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

    public static void main(String[] args) throws InterruptedException, MqttException {
        sc = new SubscribeController();
        sc.connect();
    }
    

    public static SubscribeController getSc() {
        return sc;
    }

    
    
}
