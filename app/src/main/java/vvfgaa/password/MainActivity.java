package vvfgaa.password;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("7788", "onCreate: "+Aes.encrypt("劳动者享有平等就业和选择职业的权利、取得劳动报酬的权利、休息休假的权利、获得劳动安全卫生保护的权利、接受职业技能培训的权利、享受社会保险和福利的权利、提请劳动争议处理的权利以及法律规定的其他劳动权利。"));
        Log.d("7788", "onCreate: "+Aes.decrypt("51e/8vRCxmhQQ8F4tmK9Ha1KUv7yrn5TGw4YeGrdX5Y5XT5szwoZ9mufigoMLELBm0lFH55fjTa7vvspi9ntOrdAk6GGjKmMI0tTAiOdNN2wxQUnlAyJXq8WhPYcobI3CmSX9keDmllw7SL9cEXBSrJwAtBfqkfxAPGT/6iM+Z9c1xcu13EPQDAGroSkqaoB2Zv9Al7+S9MAmkcnNHPZWDRMbiEfyRFegJscsxLkrI9kBHZDKz/ku2KCD8amnwm4uMmP/rdxFW6dyN7hRJar/UBtIbmiYI1SHm6o+VAke6EPct7aoMUQJLuoXbZtdTfa4kHnKRiIGzsIMYJ/VLReXa86yFspsMmB/O4X06hkVxron6UnWTcXQfNVeNiZ6aLQXJzdLAcu1pkE6TvgaQytVg=="));
    }
}
