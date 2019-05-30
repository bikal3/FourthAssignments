package com.example.fourthassigment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fourthassigment.API.HeroAPI;
import com.example.fourthassigment.Model.ImageModel;
import com.example.fourthassigment.Model.ItemsModel;
import com.example.fourthassigment.Retrofit.RetrofilHelper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Part;

public class AddItem extends AppCompatActivity implements View.OnClickListener {
    EditText addname, addprice, adddesc;
    Button btnadditem, selectimage,imageupload;
    TextView imagename;
    ImageView itemimage;
    Uri imageUri;
    Bitmap bitmap;
    private static final int PICK_IMAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        addname = findViewById(R.id.et_name_additem);
        addprice = findViewById(R.id.et_price_additem);
        adddesc = findViewById(R.id.et_desc_additem);
        itemimage = findViewById(R.id.iv_img_additem);

        btnadditem = findViewById(R.id.btn_add_additem);
        selectimage = findViewById(R.id.imagebutton);
        imageupload=findViewById(R.id.imageupload);
        imagename=findViewById(R.id.tv_img_imgname_additem);

        btnadditem.setOnClickListener(this);
        selectimage.setOnClickListener(this);
        imageupload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_additem:
                Save();
                break;
            case R.id.imagebutton:
                Opengallery();
                break;
            case R.id.imageupload:
                uploadImage(bitmap);

                break;



        }
    }

    private void uploadImage(Bitmap bitmap) {
        ByteArrayOutputStream stream= new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, stream);
        byte[] bytes=stream.toByteArray();
        try{

            File file= new File(this.getCacheDir(),"image.jpeg");
            file.createNewFile();
            FileOutputStream fos= new FileOutputStream(file);
            fos.write(bytes);
            fos.flush();
            fos.close();

            RequestBody rb= RequestBody.create(MediaType.parse("multipart/form-data"),file);
            MultipartBody.Part body=MultipartBody.Part.createFormData("imageName",file.getName(),rb);

            HeroAPI heroAPI= RetrofilHelper.instance().create(HeroAPI.class);
            Call<String> imageModelCall=heroAPI.uploadImage(body);
            imageModelCall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Toast.makeText(AddItem.this, response.body(), Toast.LENGTH_SHORT).show();
                    imagename.setText(response.body());


                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(AddItem.this, "Error"+ t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        }
        catch (IOException e){
            e.printStackTrace();

        }
    }


    private void Opengallery() {
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery, "Choose Image"), PICK_IMAGE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            imageUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                itemimage.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();

            }

        }

    }

    private void Save() {
//        String name=addname.getText().toString();
//        String desc=adddesc.getText().toString();
//        ItemModel itemModel= new ItemModel(name,desc);

        String name = addname.getText().toString();
        String price = addprice.getText().toString();
        String desc = adddesc.getText().toString();
        String imagename=imageupload.getText().toString();

        ItemsModel itemsModel = new ItemsModel(name, price, desc);

        HeroAPI heroAPI = RetrofilHelper.instance().create(HeroAPI.class);

//        Call<Void>  itemcall= heroAPI.addHero(itemModel);
        Call<Void> itemcall = heroAPI.addItem(itemsModel);

        itemcall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(AddItem.this, "Code", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(AddItem.this, "Successfully Added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddItem.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}
