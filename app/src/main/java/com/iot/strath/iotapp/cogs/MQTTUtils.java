package com.iot.strath.iotapp.cogs;

/**
 * Created by Kelvin on 23/04/2016.
 */
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;


import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MQTTUtils extends Activity implements MqttCallback {

    private static MqttClient client;
    private Context context;

    public static MqttClient getClient() {
        return client;
    }
    private static Context mContext;
    String url = "realtime.ngi.ibm.com";

    //connect to mqtt server
    public static boolean connect(String url) {
        try {
            MemoryPersistence persistance = new MemoryPersistence();
            client = new MqttClient("tcp://" + url + ":1883", "client2", persistance);
            client.connect();
            return true;
        } catch (MqttException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //publish to mqtt topic
    public static boolean pub(String topic, String payload) {
        MqttMessage message = new MqttMessage(payload.getBytes());
        try {
            client.publish(topic, message);
            return true;
        } catch (MqttPersistenceException e) {
            e.printStackTrace();
        } catch (MqttException e) {
            e.printStackTrace();
        }
        return false;
    }

    //subscribe to mqtt topic
    public static boolean sub(String url){
        MemoryPersistence persistance = new MemoryPersistence();
        try {
            client = new MqttClient("tcp://" + url + ":1883", "client1", persistance);
            //client.setCallback(this);
            client.subscribe("KEV/Ke/Weather");
        }catch (MqttPersistenceException e){
            e.printStackTrace();
        }catch (MqttException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void connectionLost(Throwable cause) {
        // TODO Auto-generated method stub

    }

    @Override
    public void messageArrived(String topic, MqttMessage message)
            throws Exception {
        //System.out.println(message);

//        /*Intent intent = new Intent(this, MainActivity.class);
//        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);*/
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        // TODO Auto-generated method stub

    }

    public void createNotification(View view, String data) {
        // Prepare intent which is triggered if the
        // notification is selected


    }

}