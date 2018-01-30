package com.example.c3ntry.sentientcloud;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class ImageActivity extends AppCompatActivity
{
    private static final int PICK_IMAGE=10;
    Uri imageuri = null;
    ImageView img;
    private int RESULT_LOAD_IMAGE=10;
    private String imgDecodableString;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        img = (ImageView)findViewById(R.id.image_view);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.image);

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_2,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId()==android.R.id.home) {
            finish();
            return super.onOptionsItemSelected(item);
        }
        else
        {
            finish();
            return super.onOptionsItemSelected(item);
        }
    }

    public void onClick(View view)
    {

       Intent n = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(n,PICK_IMAGE);
       img.setImageURI(imageuri);
    }



    @Override
    protected void onActivityResult(int request,int result,Intent data)
    {


        super.onActivityResult(request,result,data);
        try
        {
            if(request== RESULT_LOAD_IMAGE && result==RESULT_OK && data != null)
            {
                Uri imageUri = data.getData();

                String[] filepath= {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(imageUri,filepath, null,null,null);
                cursor.moveToFirst();

                int index = cursor.getColumnIndex(filepath[0]);
                imgDecodableString = cursor.getString(index);
                cursor.close();
                img.setImageURI(imageUri);
                //img.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
            }

            else
            {
                Toast.makeText(this,"You haven't picked an Image.",Toast.LENGTH_LONG).show();
            }
        }
            catch(Exception ex)
            {
                Toast.makeText(this,"Something went wrong.",Toast.LENGTH_LONG).show();
            }

    }





}

