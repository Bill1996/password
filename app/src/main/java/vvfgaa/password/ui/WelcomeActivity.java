package vvfgaa.password.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import vvfgaa.password.R;

public class WelcomeActivity extends AppCompatActivity {

    private TextInputEditText mPw;
    private String Pw;
    private Button mLoginBtn;
    //    private Boolean loadSuccess = false;
    private TextInputLayout mUser;
    private ImageView mWrongImg;
    private TextView mWrongPw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mPw = findViewById(R.id.textPw);
        mUser = findViewById(R.id.textUser);
        mLoginBtn = findViewById(R.id.loginBtn);
        mWrongImg = findViewById(R.id.wrongImg);
        mWrongPw = findViewById(R.id.wrongPw);

        mWrongImg.setVisibility(View.GONE);
        mWrongPw.setVisibility(View.GONE);

        //进入APP前判断是否是第一次使用
//        if(loadSuccess)
//        {
        mUser.setVisibility(View.GONE);
//        }
//        else
//        {
//            mUser.setVisibility(View.VISIBLE);
//        }

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pw = mPw.getText().toString();
                if(!Pw.equals("123"))
                {
                    //显示密码错误提示
                    mWrongImg.setVisibility(View.VISIBLE);
                    mWrongPw.setVisibility(View.VISIBLE);
                    //loadSuccess = false;
                }
                else
                {
                    mWrongImg.setVisibility(View.GONE);
                    mWrongPw.setVisibility(View.GONE);
                    Intent intent=new Intent(WelcomeActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    //loadSuccess = true;
                }

            }
        });

    }

}
