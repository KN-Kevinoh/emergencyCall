package com.innovatech.urgence.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.innovatech.urgence.R;
import com.innovatech.urgence.controllers.DataBaseHandler;
import com.innovatech.urgence.model.Ambulence;
import com.innovatech.urgence.model.Medecin;
import com.innovatech.urgence.model.Patient;

public class Add_member extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_patient_or_medecin_or_ambulancier);

        final DataBaseHandler dbHandler = new DataBaseHandler(getApplicationContext());

        final EditText name = (EditText) findViewById(R.id.txt_nom);
        final EditText phoneNumber = (EditText) findViewById(R.id.txt_number);
        final EditText residence = (EditText) findViewById(R.id.txt_residence);
        final CheckBox chk_Homme =(CheckBox) findViewById(R.id.chk_H);
        final CheckBox chk_Femme = (CheckBox) findViewById(R.id.chk_F);
        final Button addBtn = (Button) findViewById(R.id.bt_add);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(name.getText().toString().isEmpty() &&
                        residence.getText().toString().isEmpty() &&
                        phoneNumber.getText().toString().isEmpty()) &&
                        (chk_Femme.isChecked() != chk_Homme.isChecked())
                  )
                {//0 add patient , 1 add medecin , 2 add ambulance
                    Intent intent = getIntent();
                    String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

                    switch (message){

                        case "0" :

                                    if(chk_Homme.isChecked())
                                    {   if (dbHandler.isPatientExist(Integer.parseInt(phoneNumber.getText().toString()))){

                                             Toast.makeText(getApplicationContext(),"Patient already registed.",Toast.LENGTH_LONG).show();

                                        }else {
                                            Patient patient = new Patient(Integer.parseInt(phoneNumber.getText().toString()),name.getText().toString(),"M",residence.getText().toString());
                                            dbHandler.addPatient(patient);
                                            Toast.makeText(getApplicationContext(),name.getText().toString() + " has been added successfully",Toast.LENGTH_LONG).show();

                                         }

                                    }
                                    else
                                    {   if(dbHandler.isPatientExist(Integer.parseInt(phoneNumber.getText().toString())))
                                        {

                                            Toast.makeText(getApplicationContext(),"Patient already registed.",Toast.LENGTH_LONG).show();
                                        }else {
                                            Patient patient = new Patient(Integer.parseInt(phoneNumber.getText().toString()),name.getText().toString(),"F",residence.getText().toString());
                                            dbHandler.addPatient(patient);
                                            Toast.makeText(getApplicationContext(),name.getText().toString() + " has been added successfully",Toast.LENGTH_LONG).show();

                                    }

                                    }
                                    break;

                        case "1":

                                    if(chk_Homme.isChecked())
                                    {
                                        if(dbHandler.isMedecinExist(Integer.parseInt(phoneNumber.getText().toString()))) {

                                            Toast.makeText(getApplicationContext(),"Doctor already registed.",Toast.LENGTH_LONG).show();

                                        }else {
                                            Medecin medecin = new Medecin(Integer.parseInt(phoneNumber.getText().toString()), name.getText().toString(), "M", residence.getText().toString());
                                            dbHandler.addMedecin(medecin);
                                            Toast.makeText(getApplicationContext(), name.getText().toString() + " has been added successfully", Toast.LENGTH_LONG).show();

                                        }
                                    }
                                    else
                                    {
                                        if(dbHandler.isMedecinExist(Integer.parseInt(phoneNumber.getText().toString()))) {


                                            Toast.makeText(getApplicationContext(),"Doctor already registed.",Toast.LENGTH_LONG).show();

                                           }else{
                                            Medecin medecin = new Medecin(Integer.parseInt(phoneNumber.getText().toString()),name.getText().toString(),"F",residence.getText().toString());
                                            dbHandler.addMedecin(medecin);
                                            Toast.makeText(getApplicationContext(),name.getText().toString() + " added successfully",Toast.LENGTH_LONG).show();

                                        }

                                    }

                            break;

                        case  "2":

                                    if(chk_Homme.isChecked())
                                    {
                                        if(dbHandler.isAmbulanceExist(Integer.parseInt(phoneNumber.getText().toString()))) {

                                            Toast.makeText(getApplicationContext(),"Ambulance already registed.",Toast.LENGTH_LONG).show();
                                        }else {
                                            Ambulence ambulence = new Ambulence(Integer.parseInt(phoneNumber.getText().toString()),name.getText().toString(),"M",residence.getText().toString());
                                            dbHandler.addAmbulence(ambulence);
                                            Toast.makeText(getApplicationContext(),name.getText().toString() + " added successfully",Toast.LENGTH_LONG).show();

                                        }
                                    }
                                    else
                                    {
                                        if(dbHandler.isAmbulanceExist(Integer.parseInt(phoneNumber.getText().toString()))) {

                                            Toast.makeText(getApplicationContext(),"Ambulance already registed.",Toast.LENGTH_LONG).show();

                                        }else {
                                            Ambulence ambulence = new Ambulence(Integer.parseInt(phoneNumber.getText().toString()),name.getText().toString(),"F",residence.getText().toString());
                                            dbHandler.addAmbulence(ambulence);
                                            Toast.makeText(getApplicationContext(),name.getText().toString() + " added successfully",Toast.LENGTH_LONG).show();

                                        }
                                    }

                            break;
                    }
                    //empty fields
                    name.setText("");
                    phoneNumber.setText("");
                    residence.setText("");
                    chk_Femme.setChecked(false);
                    chk_Homme.setChecked(false);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"All fields are required and only one checkbox must be enable! ",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
