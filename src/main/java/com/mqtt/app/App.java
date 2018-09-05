package com.mqtt.app;

import com.mqtt.app.controller.PublishController;
import com.mqtt.app.subscribe.Subscriber;
import org.eclipse.paho.client.mqttv3.MqttException;

public class App {

    public static Subscriber sub;
    public static String topic = Config.getTopic();

    public App() {
    }

    public void init(String type, String args) throws MqttException, InterruptedException {
        String op = type;

        if (op.equalsIgnoreCase("publisher") || op.equalsIgnoreCase("publish") || op.equalsIgnoreCase("pub")) {
            PublishController pub = new PublishController(args);
        }
        if (op.equalsIgnoreCase("subscriber") || op.equalsIgnoreCase("subscribe") || op.equalsIgnoreCase("sub")) {
            System.out.println("You are subscribing.");

            sub = new Subscriber();
            if (sub.connect(topic)) {
                System.out.println("Messages arrived: ");
            }
        }

    }
}
