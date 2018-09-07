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
    String arg = new String();
    Subscriber sub;

    public SubscribeController(String args) throws MqttException {
        this.repĺy = new String();
        this.currMessage = new String();
        this.arg = args;
    }

    public void init() throws MqttException {
        try {
            doOperation(this.arg);
        } catch (MqttException e) {
            System.out.println(e);
        }
    }

    private void doOperation(String op) throws MqttException {
        String topic = Config.getTopic();
        sub = new Subscriber();
        if (op.equals("in")) {
            System.out.println("You are subscribing to \"" + topic + "\".");
            
            if (this.sub.connect(topic)) {
                System.out.println("Messages arrived: ");
                setRepĺy("Connected. Last reply:");
                
            }
        }else if(op.equals("out")){
            System.out.println("You are disconnecting.");
            if(!sub.disconnect()){
                setRepĺy("Your are disconnected.");
            }else{
                setRepĺy("Something went wrong, try again.");
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
