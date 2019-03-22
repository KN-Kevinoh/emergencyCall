package com.innovatech.urgence.views;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.Toast;

import com.innovatech.urgence.R;
import com.innovatech.urgence.controllers.DataBaseHandler;
import com.innovatech.urgence.model.Ambulence;
import com.innovatech.urgence.model.Medecin;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    //use to send SMS
    public final static int SEND_SMS_PERMISSION_REQUEST_CODE = 111;
    public final static String EXTRA_MESSAGE = "code_admin";
    private List<Medecin> medecins = new ArrayList<>();
    private List<Ambulence> ambulences = new ArrayList<>();
    private String emergencyMessage = "Alert cas urgent"+"\n\n"+"https://www.google.com/maps/@6.842008,12.829584,8z";

    //Monitoring phone call events
    TelephonyManager phoneManager;
    private final static int eventsListener = PhoneStateListener.LISTEN_CALL_STATE;//& PhoneStateListener.LISTEN_CELL_LOCATION;
    //LocationManager locationManager;

    PhoneStateListener myPhoneStateListener = new PhoneStateListener() {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);

            if (state == TelephonyManager.CALL_STATE_RINGING) {

                    rejectCall();

                    Toast.makeText(MainActivity.this, "Incoming call detected "+ incomingNumber, Toast.LENGTH_SHORT).show();

                    sendMessage();

                }
        }
        /*

        @Override
        public void onCellLocationChanged(CellLocation location) {
            super.onCellLocationChanged(location);

            int cid = 0;
            int lac = 0;

            if (location != null) {
                if (location instanceof GsmCellLocation) {
                    cid = ((GsmCellLocation) location).getCid();
                    lac = ((GsmCellLocation) location).getLac();
                }
                else if (location instanceof CdmaCellLocation) {
                    cid = ((CdmaCellLocation) location).getBaseStationId();
                    lac = ((CdmaCellLocation) location).getSystemId();
                }
            }

            String cellBase = Integer.toString(lac)+"-"+Integer.toString(cid);

            Toast.makeText(getApplicationContext(), cellBase, Toast.LENGTH_LONG).show();
            Log.v("logg", "cell:"+cellBase);


        }*/
    };

    private void rejectCall(){


        try {

            // Get the getITelephony() method
            Class<?> classTelephony = Class.forName(phoneManager.getClass().getName());
            Method method = classTelephony.getDeclaredMethod("getITelephony");
            // Disable access check
            method.setAccessible(true);
            // Invoke getITelephony() to get the ITelephony interface
            Object telephonyInterface = method.invoke(phoneManager);
            // Get the endCall method from ITelephony
            Class<?> telephonyInterfaceClass =Class.forName(telephonyInterface.getClass().getName());
            Method methodEndCall = telephonyInterfaceClass.getDeclaredMethod("endCall");
            // Invoke endCall()
            methodEndCall.invoke(telephonyInterface);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

// run listing phone state call

    // ...
    @Override
    protected void onResume() {
        super.onResume();
        phoneManager.listen(myPhoneStateListener, eventsListener);
        //phoneManager.listen(myPhoneStateListener, PhoneStateListener.LISTEN_CELL_LOCATION);
    }
    @Override
    protected void onStop() {
        super.onStop();
        phoneManager.listen(myPhoneStateListener, eventsListener);
       // phoneManager.listen(myPhoneStateListener, PhoneStateListener.LISTEN_CELL_LOCATION);
    }


        // ...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phoneManager = (TelephonyManager) getSystemService(getApplicationContext().TELEPHONY_SERVICE);


        // locationManager = (LocationManager) getSystemService(getApplicationContext().LOCATION_SERVICE);


        Button bt_add_patient = (Button) findViewById(R.id.bt_add_patient);
        Button bt_delete_patient = (Button) findViewById(R.id.bt_delete_patient);
        Button bt_list_patient = (Button) findViewById(R.id.bt_list_patient);

        Button bt_add_medecin = (Button) findViewById(R.id.bt_add_medecin);
        Button bt_delete_medecin = (Button) findViewById(R.id.bt_delete_medecin);
        Button bt_list_medecin = (Button) findViewById(R.id.bt_list_medecin);

        Button bt_add_ambulance = (Button) findViewById(R.id.bt_add_ambulencier);
        Button bt_delete_ambulance = (Button) findViewById(R.id.bt_delete_ambulencier);
        Button bt_list_ambulance = (Button) findViewById(R.id.bt_list_ambulencier);

        TabHost tabhost = (TabHost) findViewById(R.id.tabhost);
        tabhost.setup();

        TabHost.TabSpec tabSpec = tabhost.newTabSpec("Patients");
        tabSpec.setContent(R.id.tabPatient);
        tabSpec.setIndicator("Patients");
        tabhost.addTab(tabSpec);

        tabSpec = tabhost.newTabSpec("Médecins");
        tabSpec.setContent(R.id.tabMedecin);
        tabSpec.setIndicator("Médecins");
        tabhost.addTab(tabSpec);

        tabSpec = tabhost.newTabSpec("Ambulances");
        tabSpec.setContent(R.id.tabAmbulance);
        tabSpec.setIndicator("Ambulances");
        tabhost.addTab(tabSpec);

        // 0 to add patient
        bt_add_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),Add_member.class);
                String message = "0";
                intent.putExtra(EXTRA_MESSAGE,message);
                startActivity(intent);
            }
        });

        // 1 to add doctor
        bt_add_medecin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),Add_member.class);
                String message = "1";
                intent.putExtra(EXTRA_MESSAGE,message);
                startActivity(intent);
            }
        });

        // 2 to add ambulance
        bt_add_ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),Add_member.class);
                String message = "2";
                intent.putExtra(EXTRA_MESSAGE,message);
                startActivity(intent);

            }
        });

        bt_delete_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),Delete_member.class);
                String message = "0";
                intent.putExtra(EXTRA_MESSAGE,message);
                startActivity(intent);
            }
        });

        bt_delete_medecin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),Delete_member.class);
                String message = "1";
                intent.putExtra(EXTRA_MESSAGE,message);
                startActivity(intent);

            }
        });

        bt_delete_ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),Delete_member.class);
                String message = "2";
                intent.putExtra(EXTRA_MESSAGE,message);
                startActivity(intent);

            }
        });


        bt_list_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),List_member.class);
                String message = "0";
                intent.putExtra(EXTRA_MESSAGE,message);
                startActivity(intent);

            }
        });


        bt_list_medecin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),List_member.class);
                String message = "1";
                intent.putExtra(EXTRA_MESSAGE,message);
                startActivity(intent);

            }
        });

        bt_list_ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),List_member.class);
                String message = "2";
                intent.putExtra(EXTRA_MESSAGE,message);
                startActivity(intent);

            }
        });


    }

    //send sms
    private boolean checkPermission(String permission) {
        int checkPermission = ContextCompat.checkSelfPermission(getApplicationContext(), permission);
        return (checkPermission == PackageManager.PERMISSION_GRANTED);
    }

    public void sms(final String phone_number){


        final DataBaseHandler dbHandler = new DataBaseHandler(getApplicationContext());
        //monitoring the success of transmission and delivery

        String SENT_SMS_ACTION = "SENT_SMS_ACTION";
        String DELIVERED_SMS_ACTION = "DELIVERED_SMS_ACTION";

        // Create the sentIntent parameter
        Intent sentIntent = new Intent(SENT_SMS_ACTION);
        PendingIntent sentPI = PendingIntent.getBroadcast(getApplicationContext(), 0, sentIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        // Create the deliveryIntent parameter
        Intent deliveryIntent = new Intent(DELIVERED_SMS_ACTION);
        PendingIntent deliverPI = PendingIntent.getBroadcast(getApplicationContext(), 0, deliveryIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Register the Broadcast Receivers
        registerReceiver(new BroadcastReceiver() {
                             @Override
                             public void onReceive(Context _context, Intent _intent)
                             {
                                 String resultText = "UNKNOWN";
                                 switch (getResultCode()) {
                                     case Activity.RESULT_OK:
                                             resultText = "SMS Sent to "+ phone_number +" Successfully!";
                                              break;
                                     case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                                         resultText = "Transmission failed : Unknow";
                                         break;
                                     case SmsManager.RESULT_ERROR_RADIO_OFF:
                                         resultText = "Transmission failed : Radio is off";
                                         break;
                                     case SmsManager.RESULT_ERROR_NULL_PDU:
                                         resultText = "Transmission failed . No PDU specified";
                                         break;
                                     case SmsManager.RESULT_ERROR_NO_SERVICE:
                                         resultText = "Transmission failed . No service";
                                         break;
                                 }
                                 dbHandler.addSmsSentState(Integer.parseInt(phone_number),resultText);
                                 Toast.makeText(_context, resultText, Toast.LENGTH_LONG).show();
                             }
                         }, new IntentFilter(SENT_SMS_ACTION));


        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context _context, Intent _intent)
            {
                String resultText = "UNKNOWN";
                switch(getResultCode()) {
                        case Activity.RESULT_OK:
                            resultText = "SMS delivery to " + phone_number + " successfully";
                            break;
                        case Activity.RESULT_CANCELED:
                            resultText = "SMS delivery to " + phone_number + " failed";
                            break;
                 }

                dbHandler.addSmsDeliverdState(Integer.parseInt(phone_number),resultText);
                Toast.makeText(_context, resultText, Toast.LENGTH_LONG).show();

            }
        }, new IntentFilter(DELIVERED_SMS_ACTION));


        //send sms
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phone_number, null, emergencyMessage,sentPI, deliverPI);

    }

    public void sendMessage() {


        final DataBaseHandler dbHandler = new DataBaseHandler(getApplicationContext());

        if (checkPermission(Manifest.permission.SEND_SMS)) {
            // ok I do nothing

        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQUEST_CODE);
        }

        if (checkPermission(Manifest.permission.SEND_SMS)) {
            try {
                if(dbHandler.getMedecinCount() == 0 || dbHandler.getAmbulanceCount() == 0 ){

                    Toast.makeText(getApplicationContext(),"Ambulances and Doctors! Both of haven't been registed.",Toast.LENGTH_LONG).show();

                }else{
                    medecins.addAll(dbHandler.getAllMedecins());
                    ambulences.addAll(dbHandler.getAllAmbulenciers());
                    for(int i = 0; i < medecins.size() ; i++){
                        sms(String.valueOf(medecins.get(i).get_tel()));
                    }
                    for(int i = 0; i< ambulences.size();i++){
                        sms(String.valueOf(ambulences.get(i).get_tel()));
                    }
                }

            }catch (Exception e){

                Toast.makeText(getApplicationContext(), "SMS failed, please try again later ! " + e.getMessage(), Toast.LENGTH_LONG).show();


            }
        } else {
            Toast.makeText(MainActivity.this, "Permission denied", Toast.LENGTH_LONG).show();
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case SEND_SMS_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // nothing done
                }
                return;
            }
        }

    }




}
