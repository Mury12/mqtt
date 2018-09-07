/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app.controller;

import com.mqtt.app.Config;
import com.mqtt.app.subscribe.Subscriber;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 *
 * @author andremury
 */
public class SubscribeController {

    String repĺy;
    String currMessage;
    Subscriber sub;

    public SubscribeController() throws MqttException {
        sub = new Subscriber();
        this.repĺy = new String();
        this.currMessage = new String();
    }

    public void init(String args) throws MqttException {
        
        try {
            doOperation(args);
        } catch (MqttException e) {
            System.out.println(e);
        }
    }

    private void doOperation(String op) throws MqttException {
        String topic = Config.getTopic();
        if (op.equals("in")) {
            System.out.println("You are subscribing to \"" + topic + "\".");

            if (this.sub.connect(topic)) {
                System.out.println("Messages arrived: ");
                setRepĺy("Connected. Last reply:");

            }
        } else if (op.equals("out")) {
            System.out.println("You are disconnecting.");
            if (!sub.disconnect()) {
                setRepĺy("Something went wrong, try again.");
            } else {
                setRepĺy("Your are disconnected.");
            }
        }
    }

    public String getRepĺy() {
        return repĺy;
    }

    public void setRepĺy(String repĺy) {
        this.repĺy = repĺy;
    }
}
