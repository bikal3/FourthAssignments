package com.example.fourthassigment.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fourthassigment.API.HeroAPI;
import com.example.fourthassigment.Dashbaord;
import com.example.fourthassigment.Model.LoginRegisterResponse;
import com.example.fourthassigment.R;
import com.example.fourthassigment.Retrofit.RetrofilHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFrag extends Fragment implements View.OnClickListener {
    EditText username,password;
    Button btnlogin;



    public LoginFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_login, container, false);
        username=view.findViewById(R.id.et_username);
        password=view.findViewById(R.id.et_password);
//        String user=username.getText().toString().trim();
//        String pass= password.getText().toString().trim();

        btnlogin=view.findViewById(R.id.btn_login);
        btnlogin.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
//                Intent intent=new Intent(getActivity(), Dashbaord.class);
//                startActivity(intent);
                checkUser();
                break;
        }

    }

    private void checkUser() {
        String user=username.getText().toString().trim();
        String pass=password.getText().toString().trim();

        HeroAPI heroAPI = RetrofilHelper.instance().create(HeroAPI.class);
        Call<String> logincall=heroAPI.login(user,pass);
        logincall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(getActivity(), "Sucess", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(),Dashbaord.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getActivity(), "Error"+ t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });





    }

}
