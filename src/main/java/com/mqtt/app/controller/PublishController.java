/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app.controller;

import com.mqtt.app.Config;
import com.mqtt.app.services.ReplierService;
import com.mqtt.app.models.Publisher;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 *
 * @author andremury
 */
public final class PublishController {



    Publisher pub;
    String values;
    String topic = Config.getTopic();
    public PublishController(String values) throws MqttException, InterruptedException {
        this.values = values;
        try {
            init();
        } catch (InterruptedException e) {
            System.out.println(e);
        } catch (MqttException e) {
            System.out.println(e);
        }
    }

    public void init() throws MqttException, InterruptedException {
        try {
            this.pub = new Publisher();
        } catch (MqttException e) {
            System.out.println(e);
        }

        if (pub.connect()) {

            Publisher.getReply();

            if (pub.publish(this.values, topic)) {
                System.out.println("Message sent to \""+topic+"\".");
            } else {
                System.out.println("Something went wrong.. Try again.");
            }
            int i = 0;
            while (i < 5 && !ReplierService.got()) {
                Thread.sleep(Config.getTimeout());
                ++i;
            }
            pub.disconnect();
        }
    }
}
