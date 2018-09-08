/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app;

import java.util.Scanner;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 *
 * @author andremury
 */
public class Publisher {
    public static void main(String[] args) throws MqttException {
        MqttClient c = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());
        MqttMessage mqs = new MqttMessage();
        Scanner s = new Scanner(System.in);
        c.connect();
        System.out.println("Digite uma mensagem: ");
        mqs.setPayload(s.nextLine().getBytes());
        c.publish("iot_data", mqs);
        c.disconnect();
    }
}
