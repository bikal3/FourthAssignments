package com.example.fourthassigment.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.fourthassigment.Dashbaord;
import com.example.fourthassigment.R;

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

        register=view.findViewById(R.id.btn_register);
        register.setOnClickListener(this);
        return  view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                Intent intent=new Intent(getActivity(), Dashbaord.class);
                startActivity(intent);
                break;

        }

    }
}
