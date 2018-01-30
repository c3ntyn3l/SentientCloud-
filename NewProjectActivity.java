package com.example.c3ntry.sentientcloud;

import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class NewProjectActivity extends AppCompatActivity {

    DatabaseSQLite myDb = new DatabaseSQLite(this);
    Spinner spiner;
    TextView name;
    TextView sdate;
    TextView edate;
    TextView manager;
    TextView description;
    AlertDialog.Builder dialog;
    ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);




        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.new_project);

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        spiner=(Spinner)findViewById(R.id.status);
        String[] status ={"Active","In-Active","Complete"};

        ArrayAdapter ad=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,status);
        spiner.setAdapter(ad);
    }

    public void onClick(final View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick a Date");
        builder.setView(R.layout.datepicker);
        builder.setPositiveButton("DONE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                DatePicker dte=(DatePicker)findViewById(R.id.datePicker);
                sdate=(TextView)findViewById(R.id.start);
              //  sdate.setText(dte.toString());
                Toast.makeText(NewProjectActivity.this, "Done Selecting date", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId()==android.R.id.home) {
            finish();
            Context context = getApplicationContext();
            Toast.makeText(context, "Unable to Create New Project.", Toast.LENGTH_LONG).show();
            return super.onOptionsItemSelected(item);
        }
        else
        {
            //StringRequest stringRequest =new StringRequest();
            progress = new ProgressDialog(this);
            dialog = new AlertDialog.Builder(getApplicationContext());
            name=(TextView)findViewById( R.id.projectname);
            sdate=(TextView)findViewById(R.id.start);
            edate=(TextView)findViewById(R.id.end);
            manager=(TextView)findViewById(R.id.assignedmanager);
            description=(TextView)findViewById(R.id.descr);
            String type ="Register";
            BackGroundActivity bck = new BackGroundActivity(this);


            if(!validate())
            {
                Toast.makeText(this, "Please fill all required fields.", Toast.LENGTH_SHORT).show();
            }
            else
            {

                progress= new ProgressDialog(this);
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setTitle("New Project");
                progress.setCancelable(false);
                progress.setMessage("Creating the New project...");
                progress.setMax(100);
                progress.show();

                bck.execute(type,name.getText().toString(),sdate.getText().toString(),edate.getText().toString(),manager.getText().toString(),spiner.getSelectedItem().toString(),description.getText().toString());

                finish();
                Toast.makeText(this, "New Project Created.", Toast.LENGTH_LONG).show();

                NotificationCompat.Builder builder= (NotificationCompat.Builder)new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Project Repository")
                        .setContentText("A new project has been created");
                NotificationManager manager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(0,builder.build());

                /*myDb.insertData(name.getText().toString(),sdate.getText().toString(),edate.getText().toString(),manager.getText().toString(),spiner.getSelectedItem().toString(),description.getText().toString());
                Cursor res = myDb.getAllData();

                while (res.moveToNext())
                {


                    Log.v("Cursor Object", DatabaseUtils.dumpCursorToString(res));
                    //StringBuffer.append("Id: "+res.getString(0)+"\n");


                }

        */
            }

            return super.onOptionsItemSelected(item);
        }
        
     
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
             if(manager.getText().toString().isEmpty())
            {
                manager.setError("Enter the Project Manager");
                valid=false;
            }
             if(description.getText().toString().isEmpty())
            {
                description.setError("Enter a brief description of the project");
                valid=false;
            }

            return valid;

        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_2,menu);

        return super.onCreateOptionsMenu(menu);
    }




}
