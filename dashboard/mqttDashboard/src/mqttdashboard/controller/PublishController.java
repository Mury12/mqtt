/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mqttdashboard.controller;

import mqttdashboard.models.Publisher;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 *
 * @author andremury
 */
public final class PublishController {



    Publisher pub;
    String values;
    String topic;
    
    public PublishController(String values, String topic) throws MqttException, InterruptedException {
        this.values = values;
        this.topic = topic;
        try {
            init();
        } catch (InterruptedException e) {
            System.out.println(e);
        } catch (MqttException e) {
            System.out.println(e);
        }
    }

    /**
     * This function is responsible for initiating the Publisher Controller
     * @throws MqttException
     * @throws InterruptedException 
     */
    public void init() throws MqttException, InterruptedException {
        try {
            this.pub = new Publisher();
        } catch (MqttException e) {
            System.out.println(e);
        }

        if (pub.connect()) {


            if (pub.publish(this.values, topic)) {
                System.out.println("Message sent to \""+topic+"\".");
            } else {
                System.out.println("Something went wrong.. Try again.");
            }
            pub.disconnect();
        }
    }
}
