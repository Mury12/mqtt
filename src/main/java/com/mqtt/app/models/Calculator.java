/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app.models;

import com.mqtt.app.services.ReplierService;

/**
 *
 * @author andremury
 */
public class Calculator {

    /**
     * This function sums an String[] of values
     *
     * @param arr the values
     * @return the result
     */
    public int sumValues(String[] arr) {
        int s = 0;
        System.out.print("Sum: ");
        System.out.print(String.join(" + ", arr));
        for (String a : arr) {
            s += Integer.parseInt(a);
        }
        System.out.println(" Done.");
        System.out.println("Result: " + s);
        ReplierService.setReply("Sum: " + String.join(" + ", arr) + " done.\n Result: " + s);
        return s;
    }

    /**
     * This function multiplies an String[] of values
     *
     * @param arr the values
     * @return the result
     */
    public double multiplyValues(String[] arr) {
        double s = 1;
        System.out.print("Multiply: ");
        System.out.print(String.join(" * ", arr));
        for (String a : arr) {
            s *= Double.parseDouble(a);
        }
        System.out.println(" Done.");
        System.out.println("Result: " + s);
        ReplierService.setReply("Multiply: " + String.join(" + ", arr) + " done.\n Result: " + s);
        return s;
    }

    /**
     * This function subtracts an String[] of values
     *
     * @param arr the values
     * @return the result
     */
    public int subtractValues(String[] arr) {
        int s = 0;
        System.out.print("Subtract: ");
        System.out.print(String.join(" - ", arr));
        for (String a : arr) {
            s -= Integer.parseInt(a);
        }
        System.out.println(" Done.");
        System.out.println("Result: " + s);
        ReplierService.setReply("Multiply: " + String.join(" + ", arr) + " done.\n Result: " + s);
        return s;
    }

    /**
     * This function divides an String[] of values
     *
     * @param arr the values
     * @return the result
     */
    public double divideValues(String[] arr) {
        double s = 1;
        int i = 0;
        System.out.print("Divide: ");
        System.out.print(String.join(" / ", arr));
        for (String a : arr) {
            if (i == 0) {
                s = Double.parseDouble(a);
            } else {
                s /= Double.parseDouble(a);
            }
            ++i;
        }
        System.out.println(" Done.");
        System.out.println("Result: " + s);
        ReplierService.setReply("Divide: " + String.join(" + ", arr) + " done.\n Result: " + s);
        return s;
    }
}
