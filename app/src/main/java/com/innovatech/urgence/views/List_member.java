package com.innovatech.urgence.views;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.innovatech.urgence.R;
import com.innovatech.urgence.controllers.DataBaseHandler;
import com.innovatech.urgence.model.Ambulence;
import com.innovatech.urgence.model.Medecin;
import com.innovatech.urgence.model.Member;
import com.innovatech.urgence.model.Patient;

import java.util.ArrayList;
import java.util.List;

public class List_member extends AppCompatActivity {

   private List<Medecin>  medecins = new ArrayList<>() ;
   private List<Patient> patients = new ArrayList<Patient>() ;
   private List<Ambulence> ambulences = new ArrayList<>() ;
   private ArrayAdapter<Medecin> medecinAdapter;
   private ArrayAdapter<Patient> patientAdapter;
   private ArrayAdapter<Ambulence> ambulenceAdapter;
    ListView memberListview ;
     private DataBaseHandler dbHandler  ;
    public final static String UPDATE_MESSAGE = "update_member";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        memberListview = (ListView) findViewById(R.id.list);
        /*
        memberListview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                longClikedItemIndex = position;
                return false;
            }
        });
        */

        populateList();

    }

    private void  populateList(){

        dbHandler = new DataBaseHandler(List_member.this);
        Intent intent = getIntent();
        final String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        switch (message){
            case "0" :
                        if(dbHandler.getPatientCount() != 0){
                            patients.addAll(dbHandler.getAllPatients());
                            Toast.makeText(getApplicationContext(),dbHandler.getPatientCount() +" registed",Toast.LENGTH_LONG).show();

                        }else {
                            Toast.makeText(getApplicationContext(),"Empty list - No element founded",Toast.LENGTH_LONG).show();
                        }

                        patientAdapter = new PatientListAdapter();
                        memberListview.setAdapter(patientAdapter);


                        memberListview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                            @Override
                            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                                //send array to Udapte_member
                                Bundle b=new Bundle();
                                b.putStringArray(UPDATE_MESSAGE, new String[]{message, String.valueOf(position)});
                                Intent intent=new Intent(getApplicationContext(), Update_member.class);
                                intent.putExtras(b);
                                startActivity(intent);

                                return false;
                            }
                        });

                        break;

            case "1":
                        if(dbHandler.getMedecinCount() != 0){
                            medecins.addAll(dbHandler.getAllMedecins());
                            Toast.makeText(getApplicationContext(),dbHandler.getMedecinCount() +" registed",Toast.LENGTH_LONG).show();

                        }else {
                            Toast.makeText(getApplicationContext(),"Empty list - No element founded",Toast.LENGTH_LONG).show();
                        }

                        medecinAdapter = new MedecinListAdapter();
                        memberListview.setAdapter(medecinAdapter);

                     memberListview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                            //send array to Udapte_member
                            Bundle b=new Bundle();
                            b.putStringArray(UPDATE_MESSAGE, new String[]{message, String.valueOf(position)});
                            Intent intent=new Intent(getApplicationContext(), Update_member.class);
                            intent.putExtras(b);
                            startActivity(intent);

                            return false;
                        }
                        });
                        break;

            case "2":

                        if(dbHandler.getAmbulanceCount() != 0){
                            ambulences.addAll(dbHandler.getAllAmbulenciers());
                            Toast.makeText(getApplicationContext(),dbHandler.getAmbulanceCount() +" registed",Toast.LENGTH_LONG).show();

                        }else {
                            Toast.makeText(getApplicationContext(),"Empty list - No element founded",Toast.LENGTH_LONG).show();
                        }

                        ambulenceAdapter = new AmbulanceListAdapter();
                        memberListview.setAdapter(ambulenceAdapter);


                        memberListview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                            @Override
                            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                                //send array to Udapte_member
                                Bundle b=new Bundle();
                                b.putStringArray(UPDATE_MESSAGE, new String[]{message, String.valueOf(position)});
                                Intent intent=new Intent(getApplicationContext(), Update_member.class);
                                intent.putExtras(b);
                                startActivity(intent);

                                return false;
                            }
                        });

                        break;

        }
    }

// List adapter for patients

    private class PatientListAdapter extends ArrayAdapter<Patient>{

        public PatientListAdapter(){

            super(List_member.this,R.layout.listview_item,patients);

        }


        @Override
        public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

            if(view == null){
                view = getLayoutInflater().inflate(R.layout.listview_item,parent,false);
            }
            Patient currentPatient = patients.get(position);

            TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_name.setText(currentPatient.get_name());
            TextView tv_residence = (TextView) view.findViewById(R.id.tv_residence);
            tv_residence.setText(currentPatient.get_residence());
            TextView tv_phoneNumber = (TextView) view.findViewById(R.id.tv_number);
            tv_phoneNumber.setText(String.valueOf(currentPatient.get_tel()));

            return view;
        }
    }

  // List adapter for doctors

    private class MedecinListAdapter extends ArrayAdapter<Medecin>{

        public MedecinListAdapter(){

            super(List_member.this,R.layout.listview_item,medecins);

        }


        @Override
        public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

            if(view == null){
                view = getLayoutInflater().inflate(R.layout.listview_item,parent,false);
            }
            Medecin currentMedecin = medecins.get(position);

            TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_name.setText(currentMedecin.get_name());
            TextView tv_residence = (TextView) view.findViewById(R.id.tv_residence);
            tv_residence.setText(currentMedecin.get_residence());
            TextView tv_phoneNumber = (TextView) view.findViewById(R.id.tv_number);
            tv_phoneNumber.setText(String.valueOf(currentMedecin.get_tel()));

            return view;
        }
    }

 //List adapter for Ambulances

    private class AmbulanceListAdapter extends ArrayAdapter<Ambulence>{

        public AmbulanceListAdapter(){

            super(List_member.this,R.layout.listview_item,ambulences);

        }


        @Override
        public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

            if(view == null){
                view = getLayoutInflater().inflate(R.layout.listview_item,parent,false);
            }
            Ambulence currentAmbulance = ambulences.get(position);

            TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_name.setText(currentAmbulance.get_name());
            TextView tv_residence = (TextView) view.findViewById(R.id.tv_residence);
            tv_residence.setText(currentAmbulance.get_residence());
            TextView tv_phoneNumber = (TextView) view.findViewById(R.id.tv_number);
            tv_phoneNumber.setText(String.valueOf(currentAmbulance.get_tel()));

            return view;
        }
    }


}
