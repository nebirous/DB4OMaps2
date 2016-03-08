package com.example.nebirous.DB4OMaps;

import android.content.Context;
import android.content.Intent;


public class BroadcastReceiver extends android.content.BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        final Context c = context;
        Intent serviceIntent = new Intent(context,ServicioLocalizacion.class);
        context.startService(serviceIntent);
    }
}