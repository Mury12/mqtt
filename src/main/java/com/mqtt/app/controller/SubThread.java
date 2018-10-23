/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 *
 * @author andremury
 */
public class SubThread extends Thread {


    public SubThread() {
    }

    @Override
    public void run() {
        try {
            SubscribeController sc = new SubscribeController();
            sc.connect();
        } catch (MqttException ex) {
            Logger.getLogger(SubThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    public void watch() {
        this.start();
    }

}
