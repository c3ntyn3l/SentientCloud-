package com.example.c3ntry.sentientcloud;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by c3ntyn3l on 6/1/2017.
 */

public class BackGroundActivity extends AsyncTask<String,Void,String>
{
    public final Context context;
    AlertDialog dialog;

    BackGroundActivity(Context ctx)
    {
        this.context = ctx;

    }



    @Override
    protected String doInBackground(String... params) {
        String login_url = "http:// 192.168.137.1/login.php";
        String newproject_url = "http://192.168.1.2/newproject.php";
        String newmanager_url="http://192.168.1.2/newmanager.php";
        String newtask_url="http://192.168.1.2/newtask.php";
        String newmember_url="http://192.168.1.2/newmember.php";
        String type = params[0];
        String Email = params[1];
        String Password = params[2];

        URL url=null;
        if (type.equals("login"))
        {
            try {
                url = new URL(login_url);
                HttpURLConnection http = (HttpURLConnection)url.openConnection();

                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);

                OutputStream output = http.getOutputStream();
                OutputStreamWriter outputW = new OutputStreamWriter(output, "UTF-8");

                BufferedWriter buff = new BufferedWriter(outputW);

                String post_data = URLEncoder.encode("Email", "UTF-8") + "=" + URLEncoder.encode(Email, "UTF-8") + "&" +
                        URLEncoder.encode("Password", "UTF-8") + "=" + URLEncoder.encode(Password, "UTF-8");

                buff.write(post_data);
                buff.flush();
                buff.close();


                InputStream inputS = http.getInputStream();
                InputStreamReader inputR = new InputStreamReader(inputS, "iso-8859-1");
                BufferedReader buffR = new BufferedReader(inputR);

                String result = "";
                String line = "";
                while ((line = buffR.readLine()) != null) {
                    result += line;
                }
                buffR.close();
                inputS.close();

                http.disconnect();
                return result;

            }

            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if(type.equals("Register"))
        {
            String Name = params[1];
            String StartDate = params[2];
            String EndDate = params[3];
            String Project_Manager = params[4];
            String Status = params[5];
            String Description = params[6];

            try {
                url = new URL(newproject_url);
                HttpURLConnection http = (HttpURLConnection)url.openConnection();

                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);

                OutputStream output = http.getOutputStream();
                OutputStreamWriter outputW = new OutputStreamWriter(output, "UTF-8");

                BufferedWriter buff = new BufferedWriter(outputW);

                String post_data = URLEncoder.encode("Name", "UTF-8") + "=" + URLEncoder.encode(Name, "UTF-8") + "&" +
                        URLEncoder.encode("StartDate", "UTF-8") + "=" + URLEncoder.encode(StartDate, "UTF-8") + "&" +
                        URLEncoder.encode("EndDate", "UTF-8") + "=" + URLEncoder.encode(EndDate, "UTF-8") + "&" +
                        URLEncoder.encode("Project_Manager", "UTF-8") + "=" + URLEncoder.encode(Project_Manager, "UTF-8") + "&" +
                        URLEncoder.encode("Status", "UTF-8") + "=" + URLEncoder.encode(Status, "UTF-8") + "&" +
                        URLEncoder.encode("Description", "UTF-8") + "=" + URLEncoder.encode(Description, "UTF-8");

                buff.write(post_data);
                buff.flush();
                buff.close();


                InputStream inputS = http.getInputStream();
                InputStreamReader inputR = new InputStreamReader(inputS, "iso-8859-1");
                BufferedReader buffR = new BufferedReader(inputR);

                String result = "";
                String line = "";
                while ((line = buffR.readLine()) != null) {
                    result += line;
                }
                buffR.close();
                inputS.close();

                http.disconnect();
                return result;

            }

            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("Task"))
        {
            String Name = params[1];
            String StartDate = params[2];
            String EndDate = params[3];
            String Team_Member = params[4];
            String Status = params[5];
            String Description = params[6];

            try {
                url = new URL(newtask_url);
                HttpURLConnection http = (HttpURLConnection)url.openConnection();

                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);

                OutputStream output = http.getOutputStream();
                OutputStreamWriter outputW = new OutputStreamWriter(output, "UTF-8");

                BufferedWriter buff = new BufferedWriter(outputW);

                String post_data = URLEncoder.encode("Name", "UTF-8") + "=" + URLEncoder.encode(Name, "UTF-8") + "&" +
                        URLEncoder.encode("StartDate", "UTF-8") + "=" + URLEncoder.encode(StartDate, "UTF-8") + "&" +
                        URLEncoder.encode("EndDate", "UTF-8") + "=" + URLEncoder.encode(EndDate, "UTF-8") + "&" +
                        URLEncoder.encode("Team_Member", "UTF-8") + "=" + URLEncoder.encode(Team_Member, "UTF-8") + "&" +
                        URLEncoder.encode("Status", "UTF-8") + "=" + URLEncoder.encode(Status, "UTF-8") + "&" +
                        URLEncoder.encode("Description", "UTF-8") + "=" + URLEncoder.encode(Description, "UTF-8");

                buff.write(post_data);
                buff.flush();
                buff.close();


                InputStream inputS = http.getInputStream();
                InputStreamReader inputR = new InputStreamReader(inputS, "iso-8859-1");
                BufferedReader buffR = new BufferedReader(inputR);

                String result = "";
                String line = "";
                while ((line = buffR.readLine()) != null) {
                    result += line;
                }
                buffR.close();
                inputS.close();

                http.disconnect();
                return result;

            }

            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("Manager"))
        {
            String firstname = params[1];
            String lastname = params[2];
            String address = params[3];
            String email = params[4];
            String password = params[5];
            String privy = params[6];

            try {
                url = new URL(newmanager_url);
                HttpURLConnection http = (HttpURLConnection)url.openConnection();

                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);

                OutputStream output = http.getOutputStream();
                OutputStreamWriter outputW = new OutputStreamWriter(output, "UTF-8");

                BufferedWriter buff = new BufferedWriter(outputW);

                String post_data = URLEncoder.encode("firstname", "UTF-8") + "=" + URLEncoder.encode(firstname, "UTF-8") + "&" +
                        URLEncoder.encode("lastname", "UTF-8") + "=" + URLEncoder.encode(lastname, "UTF-8") + "&" +
                        URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8") + "&" +
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&" +
                        URLEncoder.encode("privy", "UTF-8") + "=" + URLEncoder.encode(privy, "UTF-8");

                buff.write(post_data);
                buff.flush();
                buff.close();


                InputStream inputS = http.getInputStream();
                InputStreamReader inputR = new InputStreamReader(inputS, "iso-8859-1");
                BufferedReader buffR = new BufferedReader(inputR);

                String result = "";
                String line = "";
                while ((line = buffR.readLine()) != null) {
                    result += line;
                }
                buffR.close();
                inputS.close();

                http.disconnect();
                return result;

            }

            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("Member"))
        {
            String firstname = params[1];
            String lastname = params[2];
            String address = params[3];
            String email = params[4];
            String password = params[5];
            String privy = params[6];

            try {
                url = new URL(newmember_url);
                HttpURLConnection http = (HttpURLConnection)url.openConnection();

                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);

                OutputStream output = http.getOutputStream();
                OutputStreamWriter outputW = new OutputStreamWriter(output, "UTF-8");

                BufferedWriter buff = new BufferedWriter(outputW);

                String post_data = URLEncoder.encode("firstname", "UTF-8") + "=" + URLEncoder.encode(firstname, "UTF-8") + "&" +
                        URLEncoder.encode("lastname", "UTF-8") + "=" + URLEncoder.encode(lastname, "UTF-8") + "&" +
                        URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8") + "&" +
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&" +
                        URLEncoder.encode("privy", "UTF-8") + "=" + URLEncoder.encode(privy, "UTF-8");

                buff.write(post_data);
                buff.flush();
                buff.close();


                InputStream inputS = http.getInputStream();
                InputStreamReader inputR = new InputStreamReader(inputS, "iso-8859-1");
                BufferedReader buffR = new BufferedReader(inputR);

                String result = "";
                String line = "";
                while ((line = buffR.readLine()) != null) {
                    result += line;
                }
                buffR.close();
                inputS.close();

                http.disconnect();
                return result;

            }

            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPreExecute() {


    }

   @Override
    protected void onPostExecute(String result)
   {
       switch (result) {
           case "Successful": {

               dialog = new AlertDialog.Builder(context).create();
               dialog.setTitle("Notification");
               dialog.setMessage("Welcome to the Cloud");
               dialog.show();
               Intent i = new Intent(context.getApplicationContext(), AdminisActivity.class);
               context.startActivity(i);
               break;
           }
           case "Invalid": {
               dialog = new AlertDialog.Builder(context).create();
               dialog.setTitle("Notification");
               dialog.setMessage("Invalid password or email");
               dialog.show();
               Intent i = new Intent(context.getApplicationContext(), LoginActivity.class);
               context.startActivity(i);
               break;
           }
           case "lost":
               dialog = new AlertDialog.Builder(context).create();
               dialog.setTitle("Notification");
               dialog.setMessage("Lost server communication");
               dialog.show();
               break;
           default:

               break;
       }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
