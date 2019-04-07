package vvfgaa.password;

import androidx.annotation.Keep;

@Keep
class Aes {

    private Aes(){}
    static native String encrypt(String in);
    static native String decrypt(String in);
    static native boolean setMasterKey(String masterKey, String key);
}
