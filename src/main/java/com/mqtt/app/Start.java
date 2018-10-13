package com.mqtt.app;

import com.mqtt.app.controller.SubscribeController;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 *
 * @author andremury
 */
public class Start {

     static SubscribeController sc;

    public static void main(String[] args) throws InterruptedException, MqttException {
        sc = new SubscribeController();
        sc.connect();
    }

}
