package com.innovatech.urgence.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.innovatech.urgence.model.Ambulence;
import com.innovatech.urgence.model.Medecin;
import com.innovatech.urgence.model.Patient;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by knk on 12/16/18.
 */

public class DataBaseHandler extends SQLiteOpenHelper
{
    private static final  int DATABASE_VERSION = 1;
    private static final  String DATABASE_NAME = "emergency",
            TAB_MEDECIN = "tb_medecin",
            TAB_PATIENT = "tb_patient",
            TAB_SENT_SMS = "tb_sent_sms",
            TAB_DELIVERED_SMS = "tb_delived_sms",
            TAB_AMBULENCE = "tb_ambulence",
            COL_NAME = "col_name",
            COL_SEX = "col_sex",
            COL_TEL = "col_tel",
            COL_RESIDENCE = "col_residence",
            COL_SMSSTATE = "col_smsState" ,
            COL_DATE = "col_date";

    public DataBaseHandler(Context context){

        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override

    public void  onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + TAB_MEDECIN + "(" +
                COL_TEL + " INTEGER PRIMARY KEY," +
                COL_NAME + " TEXT," +
                COL_SEX + " TEXT," +
                COL_RESIDENCE + " TEXT)"

        );

        db.execSQL("CREATE TABLE " + TAB_PATIENT + "(" +
                COL_TEL + " INTEGER PRIMARY KEY," +
                COL_NAME + " TEXT," +
                COL_SEX + " TEXT," +
                COL_RESIDENCE + " TEXT)"

        );

        db.execSQL("CREATE TABLE " + TAB_AMBULENCE + "(" +
                COL_TEL + " INTEGER PRIMARY KEY," +
                COL_NAME + " TEXT," +
                COL_SEX + " TEXT," +
                COL_RESIDENCE + " TEXT)"

        );

        db.execSQL("CREATE TABLE " + TAB_SENT_SMS + "(" +
                COL_TEL + " INTEGER PRIMARY KEY," +
                COL_SMSSTATE + " TEXT," +
                COL_DATE + " TEXT)"

        );

        db.execSQL("CREATE TABLE " + TAB_DELIVERED_SMS + "(" +
                COL_TEL + " INTEGER PRIMARY KEY," +
                COL_SMSSTATE + " TEXT," +
                COL_DATE + " TEXT)"

        );

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TAB_AMBULENCE);
        db.execSQL("DROP TABLE IF EXISTS " + TAB_PATIENT);
        db.execSQL("DROP TABLE IF EXISTS " + TAB_MEDECIN);

        db.execSQL("DROP TABLE IF EXISTS " + TAB_SENT_SMS);
        db.execSQL("DROP TABLE IF EXISTS " + TAB_DELIVERED_SMS);

        onCreate(db);

    }

    // add sms state

    public void addSmsSentState(int tel , String state){

        SQLiteDatabase db = getWritableDatabase();
        String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        ContentValues values = new ContentValues();

        values.put(COL_TEL, tel);
        values.put(COL_SMSSTATE,state);
        values.put(COL_DATE, mydate);

        db.insert(TAB_SENT_SMS,null,values);
        db.close();

    }

    public void addSmsDeliverdState(int tel , String state){

        SQLiteDatabase db = getWritableDatabase();
        String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        ContentValues values = new ContentValues();

        values.put(COL_TEL, tel);
        values.put(COL_SMSSTATE,state);
        values.put(COL_DATE, mydate);

        db.insert(TAB_DELIVERED_SMS,null,values);
        db.close();

    }


// add tuple medecin , patient and ambulence

    public void addMedecin (Medecin medecin){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COL_TEL, medecin.get_tel());
        values.put(COL_NAME, medecin.get_name());
        values.put(COL_SEX, medecin.get_sexe());
        values.put(COL_RESIDENCE,medecin.get_residence());

        db.insert(TAB_MEDECIN,null,values);
        db.close();
    }

    public void addPatient (Patient patient){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_TEL, patient.get_tel());
        values.put(COL_NAME, patient.get_name());
        values.put(COL_SEX, patient.get_sexe());
        values.put(COL_RESIDENCE,patient.get_residence());

        db.insert(TAB_PATIENT,null,values);
        db.close();
    }

    public void addAmbulence (Ambulence ambulence){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_TEL, ambulence.get_tel());
        values.put(COL_NAME, ambulence.get_name());
        values.put(COL_SEX, ambulence.get_sexe());
        values.put(COL_RESIDENCE,ambulence.get_residence());

        db.insert(TAB_AMBULENCE,null,values);
        db.close();
    }

    //update tuple  medecin , patient and ambulence

    public int updateMedecin (Medecin medecin){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COL_TEL, medecin.get_tel());
        values.put(COL_NAME, medecin.get_name());
        values.put(COL_SEX, medecin.get_sexe());
        values.put(COL_RESIDENCE,medecin.get_residence());

        int rowsAffected = db.update(TAB_MEDECIN , values , COL_TEL + "=?" , new String[] { String.valueOf(medecin.get_tel())});
        db.close();

        return  rowsAffected;
    }

    public int updatePatient (Patient patient){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_TEL, patient.get_tel());
        values.put(COL_NAME, patient.get_name());
        values.put(COL_SEX, patient.get_sexe());
        values.put(COL_RESIDENCE,patient.get_residence());

        int rowsAffected = db.update(TAB_PATIENT , values , COL_TEL + "=?" , new String[] { String.valueOf(patient.get_tel())});
        db.close();

        return  rowsAffected;
    }

    public int updateAmbulance (Ambulence ambulence){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_TEL, ambulence.get_tel());
        values.put(COL_NAME, ambulence.get_name());
        values.put(COL_SEX, ambulence.get_sexe());
        values.put(COL_RESIDENCE,ambulence.get_residence());

        int rowsAffected = db.update(TAB_AMBULENCE , values , COL_TEL + "=?" , new String[] { String.valueOf( ambulence.get_tel())});
        db.close();

        return rowsAffected;
    }


    //delete tuple  medecin , patient and ambulence

    public int deleteMedecin(int medecinNumber){

        SQLiteDatabase db = getWritableDatabase();
        int nb_row = db.delete(TAB_MEDECIN,COL_TEL + "=?", new  String[] { String.valueOf(medecinNumber) });
        db.close();
        return nb_row;
    }

    public int deleteParient(int patientNumber){

        SQLiteDatabase db = getWritableDatabase();
        int nb_row = db.delete(TAB_PATIENT,COL_TEL + "=?",new String[] { String.valueOf(patientNumber) });
        db.close();
        return nb_row;
    }

    public int deleteAmbulence(int ambulenceNumber){

        SQLiteDatabase db = getWritableDatabase();
        int nb_row = db.delete(TAB_AMBULENCE, COL_TEL + "=?",new String[] { String.valueOf(ambulenceNumber) });
        db.close();
        return nb_row;
    }

    // get tuple    medecin , patient and ambulence

    public Medecin getMedecin(int medecinNumber){

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TAB_MEDECIN,new String[] { COL_TEL, COL_NAME, COL_SEX,COL_RESIDENCE}, COL_TEL + "=?",new String[] { String.valueOf(medecinNumber) },null,null,null );
        if(cursor != null ){
            cursor.moveToFirst();
        }

        Medecin medecin = new Medecin(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3));
        db.close();
        cursor.close();

        return medecin;

    }

    public Patient getPatient(int patientNumber){

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TAB_PATIENT,new String[] { COL_TEL, COL_NAME, COL_SEX,COL_RESIDENCE}, COL_TEL + "=?",new String[] { String.valueOf(patientNumber) },null,null,null );
        if(cursor != null ){
            cursor.moveToFirst();
        }

        Patient patient = new Patient (Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3));
        db.close();
        cursor.close();

        return  patient;

    }

    public Ambulence getAmbulence(int ambulenceNomber){

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TAB_AMBULENCE,new String[] { COL_TEL, COL_NAME, COL_SEX,COL_RESIDENCE}, COL_TEL + "=?",new String[] { String.valueOf(ambulenceNomber) },null,null,null );
        if(cursor != null ){
            cursor.moveToFirst();
        }

        Ambulence ambulence = new Ambulence (Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3));
        db.close();
        cursor.close();

        return  ambulence;

    }

    //get all medecin , patient , and Ambulence

    public List<Medecin> getAllMedecins ()
    {
        List<Medecin> medecins = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TAB_MEDECIN,null);
        if(cursor.moveToFirst()){

            do{

                medecins.add(new Medecin( Integer.parseInt(cursor.getString(0)) , cursor.getString(1) , cursor.getString(2),cursor.getString(3)));

            }while (cursor.moveToNext());
        }
        db.close();
        cursor.close();

        return medecins;

    }


    public List<Patient> getAllPatients ()
    {
        List<Patient> patients = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TAB_PATIENT,null);
        if(cursor.moveToFirst()){

            do{

                patients.add(new Patient( Integer.parseInt(cursor.getString(0)) , cursor.getString(1) , cursor.getString(2),cursor.getString(3)));

            }while (cursor.moveToNext());
        }
        db.close();
        cursor.close();

        return patients;

    }

    public List<Ambulence> getAllAmbulenciers ()
    {
        List<Ambulence> ambulences  = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TAB_AMBULENCE,null);
        if(cursor.moveToFirst()){

            do{

                ambulences.add(new Ambulence( Integer.parseInt(cursor.getString(0)) , cursor.getString(1) , cursor.getString(2),cursor.getString(3)));

            }while (cursor.moveToNext());
        }
        db.close();
        cursor.close();

        return ambulences;

    }

//  Count member

    public  int getPatientCount(){

        int count;
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM  " + TAB_PATIENT, null);
        count = cursor.getCount();
        cursor.close();
        db.close();

        return  count;
    }

    public  int getMedecinCount(){

        int count;
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM  " + TAB_MEDECIN, null);
        count = cursor.getCount();
        cursor.close();
        db.close();

        return  count;
    }

    public  int getAmbulanceCount(){

        int count;
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM  " + TAB_AMBULENCE, null);
        count = cursor.getCount();
        cursor.close();
        db.close();

        return  count;
    }

    public  boolean  isPatientExist(int phone_number){

        List<Patient> patients = new ArrayList<>();
        patients.addAll(getAllPatients());
        for (int i=0;i<patients.size();i++){
            if(patients.get(i).get_tel() == phone_number)
                return true;
        }
        return false;
    }

    public boolean isMedecinExist(int phone_number){

        List<Medecin> medecins = new ArrayList<>();
        medecins.addAll(getAllMedecins());
        for (int i=0;i < medecins.size();i++){
            if(medecins.get(i).get_tel() == phone_number){
                return true;
            }
        }
        return false;
    }

    public boolean isAmbulanceExist(int phone_number){

        List<Ambulence> ambulences = new ArrayList<>();
        ambulences.addAll(getAllAmbulenciers());
        for (int i=0;i< ambulences.size();i++){
            if(ambulences.get(i).get_tel() == phone_number){
                return true;
            }
        }
        return false;
    }


}
