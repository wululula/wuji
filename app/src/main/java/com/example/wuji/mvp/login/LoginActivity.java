package com.example.wuji.mvp.login;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.example.wuji.R;

public class LoginActivity extends AppCompatActivity{

    private LoginFragment loginFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container);
        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("navigation_bar_tint", true)) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));
        }
        if (savedInstanceState != null) {
            FragmentManager manager = getSupportFragmentManager();
            loginFragment = (LoginFragment) manager.getFragment(savedInstanceState, LoginFragment.class.getSimpleName());
        }else {
            loginFragment = LoginFragment.newInstance();
        }
    }
}
