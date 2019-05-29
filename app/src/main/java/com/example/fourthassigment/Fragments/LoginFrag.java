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
        Call<LoginRegisterResponse> usersCall=heroAPI.checkUser(user,pass);
        usersCall.enqueue(new Callback<LoginRegisterResponse>() {
            @Override
            public void onResponse(Call<LoginRegisterResponse> call, Response<LoginRegisterResponse> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(), "Please enter correct username or password", Toast.LENGTH_SHORT).show();
                } else {
                    if(response.body().isSuccess()){
                        Intent intent= new Intent(getActivity(), Dashbaord.class);
                        startActivity(intent);


                    }
                }
            }

            @Override
            public void onFailure(Call<LoginRegisterResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();

            }
        });




    }

}
