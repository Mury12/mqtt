/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app.services;

import com.mqtt.app.Config;
import java.io.IOException;

/**
 *
 * @author andremury
 */
public class SensorManager {

    private String payload;

    public SensorManager() {
    }

    public SensorManager(String payload) throws IOException {

        this.payload = payload;
        String temp = payload.split(": ")[1];
        String local = payload.split(":")[0];
        String machine = payload.split(":")[1];

        if (Double.parseDouble(temp) <= Double.parseDouble(Config.getMaxTemp())) {
            ReplierService.addAtLow(payload);
        } else {
            ReplierService.addAtHigh(payload);
        }
                
    }

}
