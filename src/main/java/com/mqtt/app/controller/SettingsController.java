/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app.controller;

import com.mqtt.app.Config;
import com.mqtt.app.generators.GUI;
import com.mqtt.app.states.MenuState;
import javafx.scene.Scene;

/**
 *
 * @author andremury
 */
public class SettingsController {
    
    String repĺy;
    String arg = new String();
    
    public SettingsController(String args) {
        this.repĺy = new String();
        this.arg = args;
    }
    
    public boolean changeHost(String ip) {
        
        Config.setHost(ip);
        
        if (Config.getHost().equals(ip)) {
            System.out.println(ip);
            setRepĺy("Host changed to " + ip);
            return true;
        }
        setRepĺy("Host changing failed. Try again.");
        return false;
    }
    
    public boolean changeTopic(String topic){
        topic = topic.replaceAll("  ", " ")
                     .replaceAll(" ", "_");
        
        Config.setTopic(topic);
        
        if (Config.getTopic().equals(topic)) {
            System.out.println(topic);
            setRepĺy("Topic changed to " + topic);
            return true;
        }
        setRepĺy("Host changing failed. Try again.");
        
        return false;
    }
    
    public void init() {
        doOperation(this.arg);
    }
    
    private boolean restoreDefaults() {
        Config.restoreDefaults();
        setRepĺy("Settings restored default values.");
        return true;
    }
    
    private void doOperation(String args) {
        String[] values = this.unjoin(args, "::");
        
        if (values[1].equals("changeHost")) {
            this.changeHost(values[0]);
        } else if (values[1].equals("restDefaults")) {
            this.restoreDefaults();
        }else if(values[1].equals("changeTopic")){
            this.changeTopic(values[0]);
        }
        
    }
    
    private String[] unjoin(String payload, String delimiter) {
        payload = payload.trim();
        
        while (payload.contains("  ")) {
            payload = payload.replaceAll("  ", " ");
        }
        
        return payload.split(delimiter);
    }
    
    public String getRepĺy() {
        return repĺy;
    }
    
    public void setRepĺy(String repĺy) {
        this.repĺy = repĺy;
    }
    

    
}
