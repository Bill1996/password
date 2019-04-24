package vvfgaa.password.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import androidx.fragment.app.Fragment;
import vvfgaa.password.R;

public class FirstLoginFragment extends Fragment {
    private TextInputEditText mPw, mRePw;
    private String Pw, rePw;
    private MaterialButton mLoginBtn;
    private ImageView mWrongImg;
    private TextView mWrongPw;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle
        savedInstanceState){
            View v = inflater.inflate(R.layout.fragment_first_login, container, false);

            mPw = v.findViewById(R.id.textPw);
            mRePw = v.findViewById(R.id.textRePw);
            mLoginBtn = v.findViewById(R.id.loginBtn);
            mWrongImg = v.findViewById(R.id.wrongImg);
            mWrongPw = v.findViewById(R.id.wrongPw);

            mWrongImg.setVisibility(View.GONE);
            mWrongPw.setVisibility(View.GONE);

            mLoginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Pw = mPw.getText().toString();
                    rePw = mRePw.getText().toString();
                    if (!Pw.equals(rePw))
                    {
//                    loadSuccess = false;
                        mWrongPw.setText("两次输入密码不一致");
                        mWrongImg.setVisibility(View.VISIBLE);
                        mWrongPw.setVisibility(View.VISIBLE);
                    }
                    else if(Pw.length()==0)
                    {
                        mWrongPw.setText("请输入密码");
                        mWrongImg.setVisibility(View.VISIBLE);
                        mWrongPw.setVisibility(View.VISIBLE);
                    }
                    else {
                        mWrongImg.setVisibility(View.GONE);
                        mWrongPw.setVisibility(View.GONE);
                        Snackbar.make(v, "注册成功！", Snackbar.LENGTH_SHORT).show();

//                        Intent intent=new Intent(MainActivity.this,AccountListActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);
                        //                    loadSuccess = true;
                    }
                }
            });
                return v;
        }
    
}