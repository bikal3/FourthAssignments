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
import com.example.fourthassigment.Model.UserModel;
import com.example.fourthassigment.R;
import com.example.fourthassigment.Retrofit.RetrofilHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFrag extends Fragment implements View.OnClickListener {
    EditText firstname,lastname,username,password;
    Button register;


    public RegisterFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_register, container, false);

        firstname=view.findViewById(R.id.et_firstname);
        lastname=view.findViewById(R.id.et_lastname);
        username=view.findViewById(R.id.et_usernameregister);
        password=view.findViewById(R.id.et_passwordregister);

        register=view.findViewById(R.id.btn_register);
        register.setOnClickListener(this);
        return  view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                Register();
                break;

        }

    }

    private void Register() {
        String fname=firstname.getText().toString();
        String lname=lastname.getText().toString();
        String user=username.getText().toString();
        String pass=password.getText().toString();

        UserModel userModel=new UserModel(fname,lname,user,pass);
        HeroAPI heroAPI = RetrofilHelper.instance().create(HeroAPI.class);
        Call<Void> usercall=heroAPI.addUser(userModel);

        usercall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Code", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getActivity(), "Successfully Added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
