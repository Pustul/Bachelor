package ch.unige.carron8.bachelor.controllers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.util.Log;
import android.widget.TextView;

import java.util.HashMap;

import ch.unige.carron8.bachelor.R;
import ch.unige.carron8.bachelor.models.BroadcastTypes;
import ch.unige.carron8.bachelor.views.MainActivity;

/**
 * Updates the user interface by receiving broadcasts
 * Created by Blaise on 01.05.2015.
 */
public class UIReceiver extends BroadcastReceiver {
    private MainActivity mContext;

    public UIReceiver(MainActivity context) {
        mContext = context;
    }

    //TODO: Update the data of multiple sensors
    @Override
    public void onReceive(Context context, Intent intent) {
        /*if (intent.getAction().equals(String.valueOf(Sensor.TYPE_LIGHT))) {
            Float data = intent.getFloatExtra(BroadcastTypes.BCAST_EXTRA_SENSOR_DATA.toString(), 0);
            HashMap<String, String> dataDisplay = new HashMap<String, String>();
            dataDisplay.put("sensor_name","Light report:");
            dataDisplay.put("sensor_data",String.valueOf(data) + " lx");
            mContext.addSensor(dataDisplay);
        }else if(intent.getAction().equals(String.valueOf(Sensor.TYPE_AMBIENT_TEMPERATURE))){
            Float data = intent.getFloatExtra(BroadcastTypes.BCAST_EXTRA_SENSOR_DATA.toString(), 0);
            HashMap<String, String> dataDisplay = new HashMap<String, String>();
            dataDisplay.put("sensor_name","Temperature report:");
            dataDisplay.put("sensor_data",String.valueOf(data) + " °C");
            mContext.addSensor(dataDisplay);
        }else*/ if (intent.getAction().equals(BroadcastTypes.BCAST_TYPE_SERVER_STATUS.toString())) {
            String response = intent.getStringExtra(BroadcastTypes.BCAST_EXTRA_SERVER_RESPONSE.toString());
            TextView server = (TextView) mContext.findViewById(R.id.server_display_status);
            server.setText(response);
        }else{
            Float data = intent.getFloatExtra(BroadcastTypes.BCAST_EXTRA_SENSOR_DATA.toString(), 0);
            HashMap<String, String> dataDisplay = new HashMap<String, String>();
            dataDisplay.put("sensor_name",SensorController.getStringType(Integer.valueOf(intent.getAction())));
            dataDisplay.put("sensor_data",String.valueOf(data)+" "+SensorController.getStringUnit(Integer.valueOf(intent.getAction())));
            mContext.addSensor(dataDisplay);
        }
    }
}
