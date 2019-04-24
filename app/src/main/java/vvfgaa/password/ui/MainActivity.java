package vvfgaa.password.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import vvfgaa.password.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            KeyListFragment fragment = new KeyListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, KeyListFragment.TAG).commit();
        }
    }
}
