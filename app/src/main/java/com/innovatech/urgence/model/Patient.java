package com.innovatech.urgence.model;

/**
 * Created by knk on 12/16/18.
 */

public class Patient
{
    private String _name;
    private String _sex;
    private int _tel;
    private String _residence;

    public Patient(){ super();}

   public Patient (int _tel,String _name , String _sex , String _residence){

        this._tel = _tel;
        this._name = _name;
        this._sex = _sex;
        this._residence = _residence;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public void set_sexe(String _sexe) {
        this._sex = _sexe;
    }

    public void set_tel(int _tel) {
        this._tel = _tel;
    }

    public String get_name() {

        return _name;
    }

    public String get_sexe() {
        return _sex;
    }

    public int get_tel() {
        return _tel;

    }

    public String get_residence() {
        return _residence;
    }

    public void set_residence(String _residence) {
        this._residence = _residence;
    }

}
