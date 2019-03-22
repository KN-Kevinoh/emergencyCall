package com.innovatech.urgence.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.innovatech.urgence.R;
import com.innovatech.urgence.controllers.DataBaseHandler;

public class Delete_member extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_member);

        final DataBaseHandler dbHandler =  new DataBaseHandler(getApplicationContext());
        final EditText phoneNumberEdt = (EditText) findViewById(R.id.edt_number);
        final Button deleteBtn = (Button) findViewById(R.id.bt_delete);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!phoneNumberEdt.getText().toString().isEmpty()){

                    Intent intent = getIntent();
                    String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
                    int state;
                    //0 add patient , 1 add medecin , 2 add ambulance
                    switch (message){

                        case "0":

                                state = dbHandler.deleteParient(Integer.parseInt(phoneNumberEdt.getText().toString()));
                                if(state != 0){
                                    Toast.makeText(getApplicationContext(),phoneNumberEdt.getText().toString() + " has been deleted successfully!",Toast.LENGTH_LONG).show();
                                }else {
                                    Toast.makeText(getApplicationContext(),"Patient doesn't exist!",Toast.LENGTH_LONG).show();
                                }
                                break;

                        case "1":

                                state = dbHandler.deleteMedecin(Integer.parseInt(phoneNumberEdt.getText().toString()));
                                if(state != 0){
                                    Toast.makeText(getApplicationContext(),phoneNumberEdt.getText().toString() + " has been deleted successfully!",Toast.LENGTH_LONG).show();
                                }else {
                                    Toast.makeText(getApplicationContext(),"Doctor doesn't exist!",Toast.LENGTH_LONG).show();
                                }
                                break;

                        case "2":

                                state = dbHandler.deleteAmbulence(Integer.parseInt(phoneNumberEdt.getText().toString()));
                                if(state != 0){
                                    Toast.makeText(getApplicationContext(),phoneNumberEdt.getText().toString() + " has been deleted successfully!",Toast.LENGTH_LONG).show();
                                }else {
                                    Toast.makeText(getApplicationContext(),"Ambulance doesn't exist!",Toast.LENGTH_LONG).show();
                                }

                            break;
                    }
                    //empty field
                    phoneNumberEdt.setText("");

                }else{
                    Toast.makeText(getApplicationContext(),"Phone number is required!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
