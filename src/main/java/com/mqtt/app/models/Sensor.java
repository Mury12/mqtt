/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app.models;

import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.components.Cpu;
import com.profesorfalken.jsensors.model.sensors.Fan;
import java.util.List;

/**
 *
 * @author andremury
 */
public class Sensor {

    Components components;
    List<Cpu> cpus;

    public Sensor() {
        if (cpus == null) {
            System.exit(1);
        }
    }

    public double getCpuTemp() {
        components = JSensors.get.components();
        cpus = components.cpus;
        if (cpus.get(0).sensors != null) {
            return cpus.get(0).sensors.temperatures.get(0).value;
        }
        return 0;
    }

    public void getCpuFan() {
        components = JSensors.get.components();
        cpus = components.cpus;
        for (final Cpu cpu : cpus) {
            if (cpu.sensors != null) {
                //Print fan speed
                System.out.println("Fans");
                List<Fan> fans = cpu.sensors.fans;
                for (final Fan fan : fans) {
                    System.out.println(fan.name + ": " + fan.value + " RPM");
                }
            }
        }
    }

}
