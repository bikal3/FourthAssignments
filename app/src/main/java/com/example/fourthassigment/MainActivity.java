package com.example.fourthassigment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.fourthassigment.Adapters.LoginRegisterAdapter;
import com.example.fourthassigment.Fragments.LoginFrag;
import com.example.fourthassigment.Fragments.RegisterFrag;

import retrofit2.Retrofit;
//import com.example.fourthassigment.Fragments.Login;
//import com.example.fourthassigment.Fragments.RegisterFragment;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.vp_viewpager);
        tabLayout = findViewById(R.id.tb_tabyout);

        LoginRegisterAdapter adapter= new LoginRegisterAdapter(getSupportFragmentManager());
        adapter.addFragment(new LoginFrag(),"Login");
        adapter.addFragment(new RegisterFrag(),"Register");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
