package com.innovatech.urgence.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * Created by knk on 12/17/18.
 */

public class MyBroadcastReceiver extends BroadcastReceiver
{
    public static boolean islistening = false;
    // private MainActivity mainActivity;

    @Override
    public void onReceive(Context context, Intent intent) {

        TelephonyManager phoneManger;
        phoneManger = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);

        MyPhoneStateListener customerPhoneListener = new MyPhoneStateListener();
        phoneManger.listen(customerPhoneListener,PhoneStateListener.LISTEN_CALL_STATE);

        Bundle bundle = intent.getExtras();
        String phoneNumber = bundle.getString("incoming_number");
        Toast.makeText(context,phoneNumber,Toast.LENGTH_LONG).show();

        //Be sure not to register the listener more than once!
        if(!islistening) {
            phoneManger.listen(customerPhoneListener, PhoneStateListener.LISTEN_CALL_STATE);
            islistening = true;
        }

        // for outgoing call
        String outgoingPhoneNo = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER).toString();
        // prevent outgoing call
        setResultData(null);

    }


}
