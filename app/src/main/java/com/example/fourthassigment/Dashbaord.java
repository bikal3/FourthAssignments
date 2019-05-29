package com.example.fourthassigment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Dashbaord extends AppCompatActivity implements View.OnClickListener {
    ImageButton additem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbaord);

        additem=findViewById(R.id.btn_add);
        additem.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
                Intent intent= new Intent(Dashbaord.this,AddItem.class);
                startActivity(intent);
                break;

        }
    }
}
