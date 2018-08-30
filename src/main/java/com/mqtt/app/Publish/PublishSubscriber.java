/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app.Publish;

import com.mqtt.app.Config;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * This Class represents the Publisher part of subscribing state for reply
 * receiving.
 *
 * @author andremury
 */
public class PublishSubscriber {

    /**
     * @var client This is the client instance of the MQTT.
     */
    static MqttClient client;
    /**
     * @var msg This is the message that will be published.
     */
    private String serverURI;
    private static String t;

    /**
     * This is the class constructor that will initialize every dependency and
     * parts needed to the class.
     *
     * @throws MqttException
     */
    public PublishSubscriber() throws MqttException {
        this.serverURI = Config.getFullURI();
        client = new MqttClient(serverURI, MqttClient.generateClientId());
    }

    /**
     * Creates a connection.
     *
     * @return success st
     * @param topic boolean
     * @throws MqttException
     */
    public boolean connect(String topic) throws MqttException, InterruptedException {
        t = topic;
        try {
            client.setCallback(new PublisherCallBack());
            client.connect();
            System.out.println("Subscribed to topic " + topic + ".");
            client.subscribe(t);

            return true;
        } catch (MqttException e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * This function is responsible for disconnecting the client from the
     * server.
     *
     * @return Boolean success state
     */
    public boolean disconnect() {
        try {
            client.disconnect();
            return true;
        } catch (MqttException e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * This function is responsible for unsubscribe the client from a certain
     * topic set for reply receiving.
     *
     * @return Boolean for success state.
     * @throws MqttException
     */
    public static boolean unsubscribe() throws MqttException {
        try {
            client.unsubscribe(t);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
