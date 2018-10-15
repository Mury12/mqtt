package com.mqtt.app;

import com.mqtt.app.controller.SubscribeController;
import java.util.ArrayList;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 *
 * @author andremury
 */
public class Start {

    public static SubscribeController sc;
    private static ArrayList<String> low_temps = new ArrayList<String>();
    private static ArrayList<String> high_temps = new ArrayList<String>();

    public static void main(String[] args) throws InterruptedException, MqttException {
        sc = new SubscribeController();
        sc.connect();
    }
    
    public static void addAtLow(String payload){
        low_temps.add(payload);
    }
    
    public static void addAtHigh(String payload){
        high_temps.add(payload);
    }

    public static ArrayList<String> getHigh_temps() {
        return high_temps;
    }

    public static ArrayList<String> getLow_temps() {
        return low_temps;
    }

    public static SubscribeController getSc() {
        return sc;
    }

    
    
}
