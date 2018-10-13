/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app.controller;

import com.mqtt.app.models.Sensor;

/**
 *
 * @author andremury
 */
public class SensorController {
    
    private Sensor s;

    public SensorController() {
        s = new Sensor();
    }

    public double getCpuTemp() {
        return s.getCpuTemp();
    }
    
    public void getCpuFan(){
        s.getCpuFan();
    }
    
}
