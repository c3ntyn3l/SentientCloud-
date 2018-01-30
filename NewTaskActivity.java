package com.example.c3ntry.sentientcloud;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class NewTaskActivity extends AppCompatActivity {

    TextView name;
    TextView sdate;
    TextView edate;
    TextView member;
    TextView status;
    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);



        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.new_task);

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
            Toast.makeText(context, "Unable to Create New Task.", Toast.LENGTH_LONG).show();
            return super.onOptionsItemSelected(item);
        } else {

            name=(TextView)findViewById( R.id.projectname);
            sdate=(TextView)findViewById(R.id.start);
            edate=(TextView)findViewById(R.id.end);
            member=(TextView)findViewById(R.id.assignedmember);
            status=(TextView)findViewById(R.id.status);
            description=(TextView)findViewById(R.id.descr);
            String type ="Task";
            BackGroundActivity bck = new BackGroundActivity(this);

            if(!validate())
            {
                Toast.makeText(this, "Please fill all required fields.", Toast.LENGTH_SHORT).show();
            }
            else {


                bck.execute(type,name.getText().toString(),sdate.getText().toString(),edate.getText().toString(),member.getText().toString(),status.getText().toString(),description.getText().toString());

                finish();
                Toast.makeText(this, "New Task Created.", Toast.LENGTH_LONG).show();

                NotificationCompat.Builder builder= (NotificationCompat.Builder)new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Project Repository")
                        .setContentText("A new task has been created");
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
        if(name.getText().toString().isEmpty())
        {
            name.setError("Enter the Project's name");
            valid=false;
        }
        if(sdate.getText().toString().isEmpty())
        {
            sdate.setError("Enter a Start-date");
            valid=false;
        }
        if(edate.getText().toString().isEmpty())
        {
            edate.setError("Enter an End-date");
            valid=false;
        }
        if(member.getText().toString().isEmpty())
        {
            member.setError("Enter the Project member");
            valid=false;
        }
        if(description.getText().toString().isEmpty())
        {
            description.setError("Enter a brief description of the task");
            valid=false;
        }
        if(status.getText().toString().isEmpty())
        {
            status.setError("Enter a brief description of the task");
            valid=false;
        }

        return valid;

    }

}
