/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app.states;

import com.mqtt.app.Config;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 *
 * @author andremury
 */
public class Publisher {

    /**
     * @var client This is the client instance of the MQTT.
     */
    MqttClient client;
    /**
     * @var msg This is the message that will be published.
     */
    MqttMessage msg;
    /**
     * @var serverURI This is the URI to the server connection wanted.
     */
    private String serverURI;
    private String clientId;

    /**
     * This is the class constructor that will initialize every dependency and
     * parts needed to the class.
     *
     * @throws MqttException
     */
    public Publisher() throws MqttException {
        serverURI = Config.getFullURI();
        clientId = MqttClient.generateClientId();
        this.client = new MqttClient(serverURI, clientId);
        this.msg = new MqttMessage();
    }

    public boolean publish(String message, String topic) throws MqttException {
        try {
            msg.setPayload((this.clientId + ": " + message).getBytes());
            client.publish(topic, this.msg);
            return true;
        } catch (MqttException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean connect() throws MqttException {
        try {
            this.client.connect();
            return true;
        } catch (MqttException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean disconnect() throws MqttException {
        try {
            this.client.disconnect();
            System.out.println("Client disconnected.");
            return true;
        } catch (MqttException e) {
            System.out.println(e);
        }
        return false;
    }

}
