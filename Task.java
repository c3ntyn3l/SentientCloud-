package com.example.c3ntry.sentientcloud;

/**
 * Created by c3ntry on 3/30/2017.
 */

public class Task
{
    private int _id;
    private String _Name;
    private String _Sdate;
    private String _Edate;
    private String _Manager;
    private String _Status;
    private String _Description;

    public Task(String _Name, String _Sdate, String _Edate, String _Manager, String _Status, String _Description) {
        this._Name = _Name;
        this._Sdate = _Sdate;
        this._Edate = _Edate;
        this._Manager = _Manager;
        this._Status = _Status;
        this._Description = _Description;
    }

    public String get_Name() {
        return _Name;
    }

    public void set_Name(String _Name) {
        this._Name = _Name;
    }

    public String get_Sdate() {
        return _Sdate;
    }

    public void set_Sdate(String _Sdate) {
        this._Sdate = _Sdate;
    }

    public String get_Edate() {
        return _Edate;
    }

    public void set_Edate(String _Edate) {
        this._Edate = _Edate;
    }

    public String get_Manager() {
        return _Manager;
    }

    public void set_Manager(String _Manager) {
        this._Manager = _Manager;
    }

    public String get_Status() {
        return _Status;
    }

    public void set_Status(String _Status) {
        this._Status = _Status;
    }

    public String get_Description() {
        return _Description;
    }

    public void set_Description(String _Description) {
        this._Description = _Description;
    }
}
