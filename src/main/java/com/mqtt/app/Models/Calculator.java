/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app.Models;

import com.mqtt.app.Services.Replyer;

/**
 *
 * @author andremury
 */
public class Calculator {
    
    public int sumValues(String[] arr) {
        int s = 0;
        System.out.print("Sum: ");
        System.out.print(String.join(" + ", arr));
        for (String a : arr) {
            s += Integer.parseInt(a);
        }
        System.out.println(" Done.");
        System.out.println("Result: " + s);
        Replyer.setReply("Sum: " + String.join(" + ", arr) + " done.\n Result: " + s);
        return s;
    }
}
