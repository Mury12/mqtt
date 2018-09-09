/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app.controller;

import com.mqtt.app.models.Calculator;
import com.mqtt.app.models.Publisher;
import com.mqtt.app.services.ReplierService;
import com.mqtt.app.states.SubscriberState;
import javafx.application.Platform;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * This Class is responsible for process routing.
 *
 * @author andremury
 */
public class OperationController {

    String args;
    Publisher pub;

    public OperationController(String args) {
        this.args = args;
    }

    /**
     * This function is responsible for initiating the Operation Controller.
     *
     * @throws MqttException
     */
    public void init() throws MqttException {
        try {
            pub = new Publisher();
        } catch (MqttException e) {
            System.out.println(e);
        }
        System.out.println("Operation Controller successfully initialized.");
        doOperation(args);
    }

    /**
     * This function is responsible for judging and route the requested
     * operation.
     *
     * @param payload String [option, [value1,[value2,[...]]]] Ex.: add 2 1 4 3
     * @throws MqttException
     */
    public void doOperation(String payload) throws MqttException {
        String clientId = this.getClientId(payload);
        String[] arr = this.getValues(payload);
        double reply;

        reply = filterOperation(payload, arr);

        if (callBack(Double.toString(reply), clientId)) {
            ReplierService.setReply("Reply sent to: " + clientId + " :: " + reply + ".");
        } else {
            ReplierService.setReply("Reply failed.");
        }
        System.out.println(ReplierService.getReply());
        Platform.runLater(new Runnable() {
            public void run() {
                SubscriberState.setReply();
            }
        });
    }

    /**
     * This function makes a callback to the publisher with a processed data.
     *
     * @param message String containing the message to the publisher.
     * @param clientId String containing the client ID to sent the message.
     * @return Boolean for success state.
     */
    private boolean callBack(String message, String clientId) {
        String topic = clientId;

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

    /**
     * This function is responsible for routing operations. This means that the
     * values passed on @getValues() will be sent to the @getOperation method
     * and processed in the correct function.
     *
     * @param payload String original message.
     * @param arr Array of values to be processes.
     * @return the processed reply.
     */
    private double filterOperation(String payload, String[] arr) {
        double reply = 0;

        Calculator calc = new Calculator();
        String[] values = getValues(payload);

        System.out.println(payload + " op: " + getOperation(payload) + "\t length: " + values.length + " value: " + values[0]);
        if (getOperation(payload).equalsIgnoreCase("sum")) {
            reply = calc.sumValues(values);
        } else if (getOperation(payload).equalsIgnoreCase("div")) {
            reply = calc.divideValues(values);
        } else if (getOperation(payload).equalsIgnoreCase("mul")) {
            reply = calc.multiplyValues(values);
        } else if (getOperation(payload).equalsIgnoreCase("sub")) {
            reply = calc.subtractValues(values);
        }

        return reply;
    }

    /**
     * This function returns the sender ID.
     *
     * @param message String original message.
     * @return String containing the client ID.
     */
    private String getClientId(String message) {
        return this.unjoin(message, ": ")[0];
    }

    /**
     * This function is responsible for getting the values sent from the
     * publisher. and separate into an Array of values.
     *
     * @param payload String original message.
     * @return Array of String containing the data.
     */
    private String[] getValues(String payload) {

        String v = this.unjoin(this.unjoin(payload, ": ")[1], "::")[0];
        v = v.replaceAll("[^0-9]", " ");
        return this.unjoin(v, " ");
    }

    /**
     * This function gets the requested operation.
     *
     * @param payload String original message
     * @return The operation
     */
    private String getOperation(String payload) {
        return this.unjoin(this.unjoin(payload, ": ")[1], "::")[1];
    }

    /**
     * This function is responsible for separating the parts of a message. This
     * means that the message will be split where the delimiter is planted.
     *
     * @param payload String original message received.
     * @param delimiter String delimiter to split.
     * @return Array of String.
     */
    private String[] unjoin(String payload, String delimiter) {
        payload = payload.trim();

        while (payload.contains("  ")) {
            payload = payload.replaceAll("  ", " ");
        }

        return payload.split(delimiter);
    }

    /**
     * This function prints in the console an array of String[] values.
     *
     * @param arr String[] of values to print.
     */
    void printArrayOfValues(String[] arr) {
        for (String var : arr) {
            System.out.println(var + ", ");
        }
        System.out.println("Array length: " + arr.length);
    }
}
