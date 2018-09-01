/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app.Controller;

import static com.mqtt.app.App.topic;
import com.mqtt.app.Config;
import com.mqtt.app.Services.Replyer;
import com.mqtt.app.states.Publisher;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 *
 * @author andremury
 */
public class PublishController {

    Publisher pub;

    public PublishController() throws MqttException, InterruptedException {
        try {
            init();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void init() throws MqttException, InterruptedException {
        System.out.println("You are publishing.");
        try {
            this.pub = new Publisher();
        } catch (Exception e) {
            System.out.println(e);
        }//            s.nextLine();
        if (pub.connect()) {
            String str = new String();

            System.out.println("What do you want to send?");
//                    str = s.nextLine();
            Publisher.getReply();

            if (str.equalsIgnoreCase("exit") || str.equalsIgnoreCase("quit")) {
                System.out.println("Program exiting. Farewell!");
                pub.disconnect();
                return;
            }

            if (pub.publish("1 2 3 4 5", topic)) {
                System.out.println("Message sent.");
            } else {
                System.out.println("Something went wrong.. Try again.");
            }
            while (!Replyer.got()) {
                Thread.sleep(Config.getTimeout());
            }
        }
    }

}
