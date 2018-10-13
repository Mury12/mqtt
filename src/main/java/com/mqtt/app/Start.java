package com.mqtt.app;

import com.mqtt.app.controller.SensorController;

/**
 *
 * @author andremury
 */
public class Start {
    public static SensorController sc = new SensorController();

    public static void main(String[] args) throws InterruptedException {
//        GUI.go(args);

    while(true){

        System.out.println(sc.getCpuTemp());
        Thread.sleep(1000);
        
    }

}

}
