/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app.Controller;

import com.mqtt.app.Subscribe.Publisher;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 *
 * @author andremury
 */
public class OperationController {

    String args;
    Publisher pub;

    public OperationController(String args) {
        this.args = args;
    }

    public void init() throws MqttException {
        try {
            pub = new Publisher();
        } catch (MqttException e) {
            System.out.println(e);
        }
        System.out.println("Operation Controller successfully initialized.");
        doOperation(args);
    }

    private String getClientId(String message) {
        return this.unjoin(message, ": ")[0];
    }

    private String[] getValues(String payload) {
        return this.unjoin(this.unjoin(payload, ": ")[1], " ");
    }

    private String[] unjoin(String payload, String delimiter) {
        return payload.split(delimiter);
    }

    public void doOperation(String payload) throws MqttException {
        String clientId = this.getClientId(payload);
        String[] arr = this.getValues(payload);
        String option = arr[0];
        int s = 0;
        System.out.print("Sum: ");
        System.out.print(String.join(" + ", arr));
        for (String a : arr) {
            s += Integer.parseInt(a);
        }
        System.out.println(" Done.");
        System.out.println("Result: " + s);
        if (callBack(Integer.toString(s), clientId)) {
            System.out.println("Reply sent.");
        } else {
            System.out.println("Reply failed.");
        }
    }

    private boolean callBack(String message, String clientId) {
        String topic = "andremury";

        try {
            if (pub.connect()) {
                this.pub.publish(message, topic);
                pub.disconnect();
                return true;
            }
        } catch (MqttException e) {
            System.out.println(e);
        }
        return false;
    }
}
