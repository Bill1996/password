package vvfgaa.password.ui;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import vvfgaa.password.R;

public class MainActivity extends AppCompatActivity {

    private Fragment currentFragment=new Fragment();
    private FirstLoginFragment loginFragment = new FirstLoginFragment();
    private KeyListFragment listFragment = new KeyListFragment();


    public void loginToListFragment()
    {
        switchFragment(listFragment).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchFragment(loginFragment).commit();

//        if (savedInstanceState == null) {
//            KeyListFragment fragment1 = new KeyListFragment();
//
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.fragment_container, fragment1, KeyListFragment.TAG).commit();
//        }
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.fragment_container, loginFragment).commit();
//        }
    }

    private FragmentTransaction switchFragment(Fragment targetFragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!targetFragment.isAdded())
        {
            //第一次使用switchFragment()时currentFragment为null，所以要判断一下        
            if (currentFragment != null)
            {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.fragment_container, targetFragment,targetFragment.getClass().getName());
        }
        else
         {
             transaction .hide(currentFragment).show(targetFragment);
          }
        currentFragment = targetFragment;
        return transaction;
    }
}
