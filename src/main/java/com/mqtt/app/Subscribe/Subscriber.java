/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app.Subscribe;

import com.mqtt.app.SimpleMqttCallBack;
import com.mqtt.app.SimpleMqttCallBack;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

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
    private String serverURI = "tcp://themayhem.ddns.net:1883";

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
     *
     * @return success state boolean
     * @throws MqttException
     */
    public boolean connect(String topic) throws MqttException {
        try {
            client.setCallback(new SimpleMqttCallBack());
            client.connect();
            System.out.println("Subscribed to topic " + topic + ".");
            this.client.subscribe(topic);
            return true;
        } catch (MqttException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean disconnect() {
        try {
            client.disconnect();
            return true;
        } catch (MqttException e) {
            System.out.println(e);
        }
        return false;
    }

}
