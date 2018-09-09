/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app.subscribe;

import com.mqtt.app.Config;
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
    static MqttClient client;
    /**
     * @var msg This is the message that will be published.
     */
    private String serverURI;

    /**
     * This is the class constructor that will initialize every dependency and
     * parts needed to the class.
     *
     * @throws MqttException
     */
    public Subscriber() throws MqttException {
        this.serverURI = Config.getFullURI();
        client = new MqttClient(serverURI, MqttClient.generateClientId());
    }

    /**
     * Creates a connection.
     *
     * @param topic
     * @return success state boolean
     * @throws MqttException
     */
    public static boolean connect(String topic) throws MqttException {
        try {
            client.setCallback(new SubscriberCallback());
            client.connect();
            System.out.println("Subscribed to topic " + topic + ".");
            client.subscribe(topic);
            return true;
        } catch (MqttException e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * This function is responsible for disconnecting the subscriber from the
     * host.
     *
     * @return Boolean success state.
     */
    public boolean disconnect() {
        try {
            client.unsubscribe(Config.getTopic());
            client.disconnect();
            return true;
        } catch (MqttException e) {
            System.out.println(e);
        }
        return false;
    }

}
