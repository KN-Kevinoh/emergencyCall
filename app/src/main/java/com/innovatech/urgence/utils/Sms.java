package com.innovatech.urgence.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.innovatech.urgence.views.MainActivity;

/**
 * Created by knk on 12/16/18.
 */

public class Sms
{
    //use to send SMS
    private  String emergencyMessage = "Alert cas urgent";
    private  String phoneNumberDestination = "xxxxxxxxxxx";

    private boolean checkPermission(Activity activity,String permission) {
        int checkPermission = ContextCompat.checkSelfPermission(activity, permission);
        return (checkPermission == PackageManager.PERMISSION_GRANTED);
    }

    public void sendMessage(Activity activity,int SEND_SMS_PERMISSION_REQUEST_CODE) {

        if (checkPermission(activity,Manifest.permission.SEND_SMS)) {
            // ok I do nothing

        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQUEST_CODE);
        }

        if (checkPermission(activity,Manifest.permission.SEND_SMS)) {
            try {

                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNumberDestination, null, emergencyMessage, null, null);
                Toast.makeText(activity.getApplicationContext(), "SMS Sent Successfully!", Toast.LENGTH_LONG).show();

            }catch (Exception e){

                Toast.makeText(activity.getApplicationContext(), "SMS failed, please try again later ! " + e.getMessage(), Toast.LENGTH_LONG).show();


            }
        } else {
            Toast.makeText(activity.getApplicationContext(), "Permission denied", Toast.LENGTH_LONG).show();
        }
    }
/*
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
    */

}
