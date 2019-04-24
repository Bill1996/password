package vvfgaa.password.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import vvfgaa.password.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = new FirstLoginFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
        if (savedInstanceState == null) {
            KeyListFragment fragment1 = new KeyListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment1, KeyListFragment.TAG).commit();
        }
    }
}
