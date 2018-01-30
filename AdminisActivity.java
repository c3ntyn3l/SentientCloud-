package com.example.c3ntry.sentientcloud;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class AdminisActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ImageView img;
    private int CAPUTRE;
    private TextView accountt;
    private List<List_items> list;
    SearchView searchView;

    public Boolean bool=true;
    SwipeRefreshLayout swipe;

    public int v ;
    private static final String URL="http://192.168.1.2/Recyclerview.php";
    private static final String URL2="http://192.168.1.2/username.php";
    public TextView Email;

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminis);
        Email=(TextView)findViewById(R.id.accountname);
        recyclerView=(RecyclerView)findViewById(R.id.view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        username();
       loadRecyclerviewData();

        swipe=(SwipeRefreshLayout)findViewById(R.id.swipe);
        swipe.setColorSchemeResources(R.color.trans,R.color.cardview_shadow_end_color,R.color.colorPrimary);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                swipe.setRefreshing(true);
                        UpdateRecyclerViewData();
                        swipe.setRefreshing(false);

            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.toolbar_name);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i = new Intent(Intent.ACTION_SEND);
                i.setData(Uri.parse("Email"));
                String s="recipient@email.com";
                i.putExtra(Intent.EXTRA_EMAIL,s);
                i.putExtra(Intent.EXTRA_SUBJECT,"");
                i.putExtra(Intent.EXTRA_TEXT,"Type a Message");
                i.setType("text/plain");
                Intent chooser = Intent.createChooser(i,"Send Message");
                startActivity(chooser);
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }




    public void UpdateRecyclerViewData()
    {
        list.clear();


        StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {

                try {
                    JSONObject jObj = new JSONObject(response);
                    JSONArray array = jObj.getJSONArray("emp");
                    v=array.length();
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject o = array.getJSONObject(i);

                        List_items items = new List_items(o.getString("Name"),o.getString("Description"),o.getString("StartDate"));
                        list.add(items);
                    }
                    adapter = new Adapter(getApplicationContext(),list);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {

                        Toast.makeText(AdminisActivity.this,error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }


    public void loadRecyclerviewData()
    {


        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Loading Data...");
        progress.show();

        StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                progress.dismiss();
                try {
                    JSONObject jObj = new JSONObject(response);
                    JSONArray array = jObj.getJSONArray("emp");
                        v=array.length();
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject o = array.getJSONObject(i);

                        List_items items = new List_items(o.getString("Name"),o.getString("Description"),o.getString("StartDate"));
                        list.add(items);
                    }

                    adapter = new Adapter(getApplicationContext(),list);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        progress.dismiss();
                        Toast.makeText(AdminisActivity.this,error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

        public void username()
        {
            StringRequest request = new StringRequest(Request.Method.GET, URL2, new Response.Listener<String>() {

                @Override
                public void onResponse(String response)
                {
                    try {
                        JSONObject jObj = new JSONObject(response);
                        JSONArray array = jObj.getJSONArray("emp");
                        v=array.length();


                        for(int i=0;i<array.length();i++)
                        {
                            JSONObject o = array.getJSONObject(i);

                        }


                        } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
                    new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    Toast.makeText(AdminisActivity.this,error.getMessage(), Toast.LENGTH_SHORT).show();
                }


            });

            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(request);
        }


            @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setTitle("NOTIFICATION");
            builder.setMessage("Do you want to Logout?");
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "Running Activities Terminated.", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            });

            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "Please do continue working.", Toast.LENGTH_LONG).show();
                }
            });
            builder.create().show();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.adminis, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id==R.id.refresh)
        {
            UpdateRecyclerViewData();
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nproject)
        {
            Intent i = new Intent(this,NewProjectActivity.class);
            startActivity(i);

        } else if (id == R.id.nteam)
        {
            Intent i = new Intent(this,TeamActivity.class);
            startActivity(i);
        } else if (id == R.id.nprojectm)
        {
            Intent i = new Intent(this,ProjectManagerActivity.class);
            startActivity(i);
        } else if (id == R.id.nphoto)
        {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if(i.resolveActivity(getPackageManager()) !=null)
            {
                startActivityForResult(i,CAPUTRE);
                Toast.makeText(this,"Upload Photo from Gallery after taking it.",Toast.LENGTH_LONG).show();
            }
        } else if (id == R.id.nupload)
        {
            Intent i = new Intent(this,ImageActivity.class);
            startActivity(i);
        }
        else if (id == R.id.ntask)
        {
            Intent i = new Intent(this,NewTaskActivity.class);
            startActivity(i);
        }
        else if(id == R.id.tasks)
        {
        }

        else if (id == R.id.nlogout)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setTitle("NOTIFICATION");
            builder.setMessage("Are you sure you want to Logout?");
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    Toast.makeText(getApplicationContext(),"Running Activities Terminated.",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            });

            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(),"Please do continue working.",Toast.LENGTH_LONG).show();
                }
            });
            builder.create().show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

    }


}
