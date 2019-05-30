package com.example.fourthassigment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.fourthassigment.API.HeroAPI;
import com.example.fourthassigment.Adapters.DashboardAdapter;
import com.example.fourthassigment.Model.DashModel;
import com.example.fourthassigment.Model.ItemsModel;
import com.example.fourthassigment.Retrofit.RetrofilHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dashbaord extends AppCompatActivity implements View.OnClickListener {
    ImageButton additem;
    private List<ItemsModel> itemsModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbaord);

        additem=findViewById(R.id.btn_add);
        additem.setOnClickListener(this);

        init();

    }

    private void init() {
        HeroAPI heroAPI= RetrofilHelper.instance().create(HeroAPI.class);
        final Call<List<ItemsModel>> itemscall=heroAPI.getAllData();
        itemscall.enqueue(new Callback<List<ItemsModel>>() {
            @Override
            public void onResponse(Call<List<ItemsModel>> call, Response<List<ItemsModel>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(Dashbaord.this, response.code()+": " +response.errorBody(), Toast.LENGTH_SHORT).show();
                    return;
                }

//                List<ItemsModel> itemsModelList1=response.body();

                itemsModelList=response.body();


                RecyclerView recyclerView=findViewById(R.id.rv_recyclerview);
                recyclerView.setAdapter(new DashboardAdapter(getApplicationContext(), itemsModelList));
                recyclerView.setLayoutManager(new LinearLayoutManager(Dashbaord.this));

            }

            @Override
            public void onFailure(Call<List<ItemsModel>> call, Throwable t) {
                Toast.makeText(Dashbaord.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });

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
