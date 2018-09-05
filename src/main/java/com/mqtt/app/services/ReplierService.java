/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app.services;

/**
 *
 * @author andremury
 */
public class ReplierService {
    static String reply = new String();
    public static String getReply() {
        return reply;
    }

    public static void setReply(String reply) {
        ReplierService.reply = reply;
    }


    public static boolean got() {
        if(reply.length()>0){
            return true;
        }
        resetReply();
        return false;
    }
    
    public static boolean resetReply(){
        reply = "";
        return reply.length()>0;
    }
}
