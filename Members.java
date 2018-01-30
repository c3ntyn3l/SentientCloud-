package com.example.c3ntry.sentientcloud;

/**
 * Created by c3ntry on 3/27/2017.
 */

public class Members
{
    private int _id;
    private String _Firstname;
    private String _lastname;
    private String _address;
    private String _privilege;
    private String _email;
    private String _password;

    public Members(String Firstname, String lastname, String address, String privilege, String email, String password)
    {
        this._Firstname = Firstname;
        this._lastname = lastname;
        this._address = address;
        this._privilege = privilege;
        this._email = email;
        this._password = password;
    }

    public Members()
    {}


    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_Firstname(String _Firstname) {
        this._Firstname = _Firstname;
    }

    public void set_lastname(String _lastname) {
        this._lastname = _lastname;
    }

    public void set_address(String _address) {
        this._address = _address;
    }

    public void set_privilege(String _privilege) {
        this._privilege = _privilege;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public int get_id() {
        return _id;
    }

    public String get_Firstname() {
        return _Firstname;
    }

    public String get_lastname() {
        return _lastname;
    }

    public String get_address() {
        return _address;
    }

    public String get_privilege() {
        return _privilege;
    }

    public String get_email() {
        return _email;
    }

    public String get_password() {
        return _password;
    }
}
