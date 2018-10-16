/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app.services;

import java.util.ArrayList;

/**
 *
 * @author andremury
 */
public class ReplierService {

    private static ArrayList<String> low_temps = new ArrayList<String>();
    private static ArrayList<String> high_temps = new ArrayList<String>();

    static String reply = new String();

    public static String getReply() {
        return reply;
    }

    public static void setReply(String reply) {
        ReplierService.reply = reply;
    }

    public static boolean got() {
        if (reply.length() > 0) {
            return true;
        }
        resetReply();
        return false;
    }

    public static boolean resetReply() {
        reply = "";
        return reply.length() > 0;
    }

    public static void addAtLow(String payload) {
        low_temps.add(payload);
    }

    public static void addAtHigh(String payload) {
        high_temps.add(payload);
    }

    public static ArrayList<String> getHigh_temps() {
        return high_temps;
    }

    public static ArrayList<String> getLow_temps() {
        return low_temps;
    }
}
