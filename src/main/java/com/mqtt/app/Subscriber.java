/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 *
 * @author andremury
 */
public class Subscriber {

    /**
     * @var client This is the client instance of the MQTT.
     */
    MqttClient client;
    /**
     * @var msg This is the message that will be published.
     */
    private String serverURI = "tcp://localhost:1883";

    
    /**
     * This is the class constructor that will initialize every dependency and
     * parts needed to the class.
     *
     * @throws MqttException
     */
    public Subscriber() throws MqttException {
        this.client = new MqttClient(serverURI, MqttClient.generateClientId());
    }
    /**
     * Creates a connection.
     * @throws MqttException 
     */
    public boolean connect() throws MqttException{
        try {
            client.setCallback(new SimpleMqttCallBack());
            client.connect();
            System.out.println("Client connected.");
            this.client.subscribe("iot_data");
            return true;
        } catch (MqttException e) {
            System.out.println(e);
        }
        return false;
    }

}
