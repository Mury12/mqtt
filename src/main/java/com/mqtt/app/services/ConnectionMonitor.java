/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app.services;

import com.mqtt.app.controller.SubscribeController;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 *
 * @author andremury
 */
public class ConnectionMonitor {
    
    private static String sensor_topic;
    private static SubscribeController sub;   
    
    
    public static void checkForNewSensors(SubscribeController sc) throws MqttException{
        
        if(ReplierService.getReply().contains("addSensor")){
            sensor_topic = ReplierService.getReply().split("::")[1];
            System.out.println("New connection detected. Connecting to sensor "+sensor_topic+"..");
            sub.addSensor(sensor_topic);
        }
        
    }
    
}
