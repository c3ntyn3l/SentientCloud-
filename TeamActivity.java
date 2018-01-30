package com.example.c3ntry.sentientcloud;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TeamActivity extends AppCompatActivity {


    TextView fname;
    TextView lname;
    TextView address;
    TextView email;
    TextView password;
    TextView password2;
    TextView priv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);



        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.new_team);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            Context context = getApplicationContext();
            Toast.makeText(context, "Unable to Create New Team Member.", Toast.LENGTH_LONG).show();
            return super.onOptionsItemSelected(item);
        } else
        {
            fname=(TextView)findViewById( R.id.Firstname);
            lname=(TextView)findViewById(R.id.lastname);
            address=(TextView)findViewById(R.id.Address);
            email=(TextView)findViewById(R.id.Email);
            password=(TextView)findViewById(R.id.Password);
            password2=(TextView)findViewById(R.id.Password2);
            priv=(TextView)findViewById(R.id.privy);
            String type="Member";
            BackGroundActivity bck = new BackGroundActivity(this);

            if(!validate())
            {
                Toast.makeText(this, "Please fill all required fields.", Toast.LENGTH_SHORT).show();
            }
            else
            {


                bck.execute(type,fname.getText().toString(),lname.getText().toString(),address.getText().toString(),email.getText().toString(),password.getText().toString(),priv.getText().toString());

                finish();
                Toast.makeText(this, "New Project Member Created.", Toast.LENGTH_LONG).show();

                NotificationCompat.Builder builder= (NotificationCompat.Builder)new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Project Repository")
                        .setContentText("A new team member has been created");
                NotificationManager manager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(0,builder.build());


            }


            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_2,menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean validate()
    {
        boolean valid=true;
        if(fname.getText().toString().isEmpty())
        {
            fname.setError("Member Firstname");
            valid=false;
        }
        if(lname.getText().toString().isEmpty())
        {
            lname.setError("Member Surname");
            valid=false;
        }
        if(address.getText().toString().isEmpty())
        {
            address.setError("Member address");
            valid=false;
        }
        if(email.getText().toString().isEmpty())
        {
            email.setError("Member Email");
            valid=false;
        }
        if(password.getText().toString().isEmpty())
        {
            password.setError("Member Password");
            valid=false;
        }
        if(password2.getText().toString().isEmpty())
        {
            password2.setError("Verify Password");
            valid=false;
        }
        if(priv.getText().toString().isEmpty())
        {
            priv.setError("Member Privilege");
            valid=false;
        }

        return valid;

    }



}
