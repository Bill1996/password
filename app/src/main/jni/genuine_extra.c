#include <string.h>
#include <malloc.h>
#include "aes.h"
#include "base64.h"
#include "genuine_extra.h"

static const uint8_t key[] = {0x60, 0x3d, 0xeb, 0x10, 0x15, 0xca, 0x71, 0xbe, 0x2b, 0x73, 0xae,
                              0xf0, 0x85, 0x7d, 0x77, 0x81,
                              0x1f, 0x35, 0x2c, 0x07, 0x3b, 0x61, 0x08, 0xd7, 0x2d, 0x98, 0x10,
                              0xa3, 0x09, 0x14, 0xdf, 0xf4};
static const uint8_t iv[] = {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b,
                             0x0c, 0x0d, 0x0e, 0x0f};


static inline size_t *findPaddingIndex(const uint8_t *str, size_t length) {
    static size_t result[] = {-1, -1};
    static size_t i;
    static size_t k;
    for (i = 0; i < length; i++) {
        char c = str[length - i];
        if (c != '\0') {
            result[0] = i;
            for (k = 0; k < AES_BLOCKLEN; ++k) {
                if (HEX[k] == c) {
                    if (0 == k) {
                        k = AES_BLOCKLEN;
                    }
                    result[1] = k;
                    return result;
                }
            }
            return result;
        }
    }
    return (size_t *)-1;
}

static inline uint8_t *getPKCS7PaddingInput(const char *in) {
    size_t inLength = strlen(in);
    size_t remainder = inLength % AES_BLOCKLEN;
    uint8_t *paddingInput;
    size_t group = inLength / AES_BLOCKLEN;
    size_t size = AES_BLOCKLEN * (group + 1);
    paddingInput = (uint8_t *) malloc(size + 1);

    size_t dif = size - inLength;
    for (size_t i = 0; i < size; i++) {
        if (i < inLength) {
            paddingInput[i] = (uint8_t) in[i];
        } else {
            if (remainder == 0) {
                paddingInput[i] = HEX[0];
            } else {
                paddingInput[i] = HEX[dif];
            }
        }
    }
    paddingInput[size] = '\0';
    return paddingInput;
}

static inline void removePKCS7Padding(uint8_t *out, const size_t inputLength) {
    size_t *result = findPaddingIndex(out, inputLength - 1);
    size_t offSetIndex = result[0];
    size_t lastChar = result[1];
    const size_t noZeroIndex = inputLength - offSetIndex;
    if (lastChar >= 0 && offSetIndex >= 0) {
        int success = 1;
        for (size_t i = 0; i < lastChar; ++i) {
            size_t index = noZeroIndex - lastChar + i;
            if (HEX[lastChar] != out[index]) {
                success = 0;
            }
        }
        if (success == 1) {
            out[noZeroIndex - lastChar] = '\0';
            memset(out + noZeroIndex - lastChar + 1, 0, (size_t) lastChar - 1);
        }
    } else {
        out[noZeroIndex] = '\0';
    }
}

char *AES_CBC_PKCS7_Encrypt(const char *in, struct AES_ctx *ctx) {
    uint8_t *paddingInput = getPKCS7PaddingInput(in);
    size_t len = strlen((const char*)paddingInput);
    AES_CBC_encrypt_buffer(ctx, paddingInput, len);
    char *base64En = b64_encode(paddingInput, len);
    free(paddingInput);
    return base64En;
}

char *AES_CBC_PKCS7_Decrypt(const char *in, struct AES_ctx *ctx) {
    size_t len = strlen(in);
    uint8_t *inputDesBase64 = b64_decode(in, len);
    const size_t inputLength = (len / 4) * 3 / AES_BLOCKLEN * AES_BLOCKLEN;
    AES_CBC_decrypt_buffer(ctx, inputDesBase64, inputLength);
    removePKCS7Padding(inputDesBase64, inputLength);
    return (char *) inputDesBase64;
}

static jstring encrypt(JNIEnv *env, jclass clazz __unused, jstring in) {
    const char *str = (*env)->GetStringUTFChars(env, in, 0);
    struct AES_ctx ctx;
    AES_init_ctx_iv(&ctx, key, iv);
    return (*env)->NewStringUTF(env, AES_CBC_PKCS7_Encrypt(str, &ctx));
}

static jstring decrypt(JNIEnv *env, jclass clazz __unused, jstring in) {
    const char *str = (*env)->GetStringUTFChars(env, in, 0);
    struct AES_ctx ctx;
    AES_init_ctx_iv(&ctx, key, iv);
    return (*env)->NewStringUTF(env, AES_CBC_PKCS7_Decrypt(str, &ctx));
}

jint JNI_OnLoad_Extra(JNIEnv *env __unused, jclass clazz __unused) {
    jclass jclass1;
    JNINativeMethod methods[2];
    if ((jclass1 = (*env)->FindClass(env, "vvfgaa/password/Aes")) == NULL) {
        return JNI_ERR;
    }
    methods[0].name = strdup("encrypt");
    methods[0].signature = strdup("(Ljava/lang/String;)Ljava/lang/String;");
    methods[0].fnPtr = encrypt;
    methods[1].name = strdup("decrypt");
    methods[1].signature = strdup("(Ljava/lang/String;)Ljava/lang/String;");
    methods[1].fnPtr = decrypt;
    return (*env)->RegisterNatives(env, jclass1, methods, 2);
}