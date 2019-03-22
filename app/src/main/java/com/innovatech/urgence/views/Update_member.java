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

import java.util.ArrayList;
import java.util.List;

public class Update_member extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_member);

        final DataBaseHandler dbHandler = new DataBaseHandler(getApplicationContext());

        final EditText name = (EditText) findViewById(R.id.txt_nom);
        final EditText phoneNumber = (EditText) findViewById(R.id.txt_number);
        final EditText residence = (EditText) findViewById(R.id.txt_residence);
        final Button updateBtn = (Button) findViewById(R.id.bt_update);


        Bundle b=this.getIntent().getExtras();
        final String[] message = b.getStringArray(List_member.UPDATE_MESSAGE);

     switch (message[0]){
            case "0":
                        final List<Patient> patients = new ArrayList<>();
                        patients.addAll(dbHandler.getAllPatients());
                        name.setText(patients.get(Integer.parseInt(message[1])).get_name());
                        name.setEnabled(false);
                        residence.setText(patients.get(Integer.parseInt(message[1])).get_residence());
                        phoneNumber.setText(String.valueOf(patients.get(Integer.parseInt(message[1])).get_tel()));

                        updateBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Patient patient = dbHandler.getPatient(patients.get(Integer.parseInt(message[1])).get_tel());
                                patient.set_tel(Integer.parseInt(phoneNumber.getText().toString()));
                                patient.set_residence(residence.getText().toString());
                                int nbRowsAffected = dbHandler.updatePatient(patient);

                                if(nbRowsAffected != 0 ){
                                    Toast.makeText(getApplicationContext(), patient.get_name() + " has been updated successfully!",Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(), patient.get_name() + " failed to update!",Toast.LENGTH_LONG).show();
                                }

                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            }
                        });

                        break;
            case "1":
                        final List<Medecin> medecins = new ArrayList<>();
                        medecins.addAll(dbHandler.getAllMedecins());
                        name.setText(medecins.get(Integer.parseInt(message[1])).get_name());
                        name.setEnabled(false);
                        residence.setText(medecins.get(Integer.parseInt(message[1])).get_residence());
                        phoneNumber.setText(String.valueOf(medecins.get(Integer.parseInt(message[1])).get_tel()));

                        updateBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Medecin medecin = dbHandler.getMedecin(medecins.get(Integer.parseInt(message[1])).get_tel());
                                medecin.set_tel(Integer.parseInt(phoneNumber.getText().toString()));
                                medecin.set_residence(residence.getText().toString());
                                int nbRowsAffected = dbHandler.updateMedecin(medecin);

                                if(nbRowsAffected != 0 ){
                                    Toast.makeText(getApplicationContext(), medecin.get_name() + " has been updated successfully!",Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(), medecin.get_name() + " failed to update!",Toast.LENGTH_LONG).show();
                                }

                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            }
                        });
                        break;
            case "2":
                        final List<Ambulence>  ambulences = new ArrayList<>();
                        ambulences.addAll(dbHandler.getAllAmbulenciers());
                        name.setText(ambulences.get(Integer.parseInt(message[1])).get_name());
                        name.setEnabled(false);
                        residence.setText(ambulences.get(Integer.parseInt(message[1])).get_residence());
                        phoneNumber.setText(String.valueOf(ambulences.get(Integer.parseInt(message[1])).get_tel()));

                        updateBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Ambulence ambulence = dbHandler.getAmbulence(ambulences.get(Integer.parseInt(message[1])).get_tel());
                                ambulence.set_tel(Integer.parseInt(phoneNumber.getText().toString()));
                                ambulence.set_residence(residence.getText().toString());
                                int nbRowsAffected = dbHandler.updateAmbulance(ambulence);

                                if(nbRowsAffected != 0 ){
                                    Toast.makeText(getApplicationContext(), ambulence.get_name() + " has been updated successfully!",Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(), ambulence.get_name() + " failed to update!",Toast.LENGTH_LONG).show();
                                }

                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            }
                        });
                        break;
        }


    }
}
