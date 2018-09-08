/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;


/**
 *
 * @author andremury
 */
public class Subscriber {
    public static void main(String[] args) throws MqttException{
        MqttClient c = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());
        c.setCallback(new SubscriberCallBack());
        c.connect();
        c.subscribe("iot_data");
    }
}
