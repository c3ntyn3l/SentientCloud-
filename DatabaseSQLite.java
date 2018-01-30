package com.example.c3ntry.sentientcloud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

/**
 * Created by c3ntyn3l on 5/29/2017.
 */

public class DatabaseSQLite extends SQLiteOpenHelper
{
    public  static final String DATABASE_NAME="SentientDataB";
    public static final String TABLE_NAME="TheProjects";
    public static final Integer Version=10;
    public static final String COL_1="ID";
    public  static final String COL_2="Name";
    public  static final String COL_3="StartDate";
    public  static final String COL_4="EndDate";
    public  static final String COL_5="Project_Manager";
    public  static final String COL_6="Description";
    public static String COL_7="Status";

    public DatabaseSQLite(Context context)
    {
        super(context,DATABASE_NAME, null,Version);
        // SQLiteDatabase db=this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {

        db.execSQL("CREATE TABLE "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, STARTDATE TEXT," +
                " ENDDATE TEXT,PROJECT_MANAGER TEXT, STATUS TEXT ,DESCRIPTION TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }

    public boolean insertData(String name,String startdate,String enddate,String status,String pm,String desc)
    {


        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,startdate);
        contentValues.put(COL_4,enddate);
        contentValues.put(COL_5,pm);
        contentValues.put(COL_7,status);
        contentValues.put(COL_6,desc);

        long result =db.insert(TABLE_NAME,null,contentValues);
//        db.execSQL("INSERT INTO "+TABLE_NAME+ "("+COL_2+","+COL_3+","+COL_4+","+COL_5+","+COL_6+","+COL_7+") VALUES('"+name+"','"+startdate+"','"+enddate+"','"+status+"','"+pm+"','"+desc+"')");
        db.close();

        if(result==-1) {
            return false;
        }
        else
        {
            return true;
        }

    }


    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from "+TABLE_NAME,null);
        return res;

    }

}
