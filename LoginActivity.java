package com.example.c3ntry.sentientcloud;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class LoginActivity extends AppCompatActivity {

    EditText vemail ;
    EditText vpassword;
    ProgressDialog progress;
    BackGroundActivity bck = new BackGroundActivity(this);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        vemail=(EditText)findViewById(R.id.email);
        vpassword=(EditText)findViewById(R.id.password);


        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.login);
        readCredentials();

    }

    public void onClick(final View view)
    {
        final String sEmail = vemail.getText().toString();
        final String sPassword = vpassword.getText().toString();


        if(vemail.getText().toString().isEmpty())
        {
            vemail.setError("Enter your email");
        }
        if(vpassword.getText().toString().isEmpty())
        {
            vpassword.setError("Enter your password");
        }
        else if(vemail.getText().toString().length()!=0 && vpassword.getText().toString().length()!=0)
        {
            saveCredentials();
            readCredentials();
            progress= new ProgressDialog(this);
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setTitle("Logging in...");
            progress.setCancelable(false);
            progress.setMessage("Establishing server communication...");
            progress.setMax(100);
            progress.show();
            String type = "login";

            bck.execute(type, sEmail, sPassword);


        }
        else
        {
            Toast.makeText(this, "Lost server communication.", Toast.LENGTH_LONG).show();
        }

    }

    public void saveCredentials()
    {

        try {
            File file = new File(getCacheDir(),"User");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
           String email= vemail.getText().toString();
           String pass= vpassword.getText().toString();
            fileOutputStream.write(email.getBytes());
            fileOutputStream.close();
            Toast.makeText(this, "Credentials Saved", Toast.LENGTH_SHORT).show();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed()
    {

    }

    public void readCredentials()
    {
        File file = new File(getCacheDir(),"User");
        try {
            FileInputStream fin = new FileInputStream(file);
            InputStreamReader input = new InputStreamReader(fin);
            BufferedReader bufferReader= new BufferedReader(input);
            StringBuilder builder = new StringBuilder();
            String line=null;
            while((line=bufferReader.readLine())!=null)
            {
                builder.append(line);
            }
            fin.close();
            input.close();
            vemail.setText(builder.toString());


        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

    }

}
