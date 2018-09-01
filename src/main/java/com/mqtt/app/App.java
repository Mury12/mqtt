package com.mqtt.app;

import com.mqtt.app.Controller.PublishController;
import com.mqtt.app.Publish.ReplyGetter;
import com.mqtt.app.Services.Replyer;
import com.mqtt.app.Subscribe.Subscriber;
import com.mqtt.app.states.Publisher;
import java.util.Scanner;
import org.eclipse.paho.client.mqttv3.MqttException;

public class App {

    public static Subscriber sub;
    public static String topic = Config.getTopic();

    public void init(String type) throws MqttException, InterruptedException {
        String op = type;
        Scanner s = new Scanner(System.in);
        System.out.println("Welcolme to the MQTT Server-client Project!");
//        System.out.println("What do you want to do?");
//        System.out.println("Publish or Subscribe: ");
//        op = s.next();

        if (op.equalsIgnoreCase("publisher") || op.equalsIgnoreCase("publish") || op.equalsIgnoreCase("pub")) {
            PublishController pub = new PublishController();
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
