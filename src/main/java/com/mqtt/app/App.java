package com.mqtt.app;

import java.util.Scanner;
import javafx.scene.web.PromptData;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * Hello world!
 *
 */
public class App {

    public static Publisher pub;
    public static Subscriber sub;

    public static void main(String[] args) throws MqttException {
        String op;
        Scanner s = new Scanner(System.in);
        System.out.println("Welcolme to the MQTT Server-client Project!");
        System.out.println("What do you want to do?");
        System.out.println("Publish or Subscribe: ");
        op = s.next();

        if (op.equalsIgnoreCase("publisher") || op.equalsIgnoreCase("publish") || op.equalsIgnoreCase("pub")) {
            pub = new Publisher();
            if (pub.connect()) {
                String str = new String();
                while (true) {
                    System.out.println("What do you want to send?");
                    str = s.next();
                    
                    if (str.equalsIgnoreCase("exit") || str.equalsIgnoreCase("quit")) {
                        System.out.println("Program exiting. Farewell");
                        pub.disconnect();
                    }
                    
                    if(pub.publish(str)){
                        System.out.println("Message sent.");
                    }else{
                        System.out.println("Something went wrong.. Try again.");
                        continue;
                    }                    
                }
            }
        }
        if(op.equalsIgnoreCase("subscriber") || op.equalsIgnoreCase("subscribe") || op.equalsIgnoreCase("sub")){
            sub = new Subscriber();
            if(sub.connect()){
                System.out.println("Messages arrived: ");
            }
        }

    }

}
