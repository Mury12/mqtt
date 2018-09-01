/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.eclipse.paho.client.mqttv3.MqttException;

public class GUI extends Application implements EventHandler<ActionEvent> {

    boolean publish = false,
            subscribe = false;

    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("MQTT Applicaton Example");
        stage.setScene(getInitialStage());
        
        stage.show();
    }

    private Scene getStage() {
        StackPane l = new StackPane();
        Scene s = new Scene(l);
        return s;
    }

    private Scene getInitialStage() {
        StackPane layout = new StackPane();
        Group left = new Group();
        Group right = new Group();
        
        final Button pub = new Button("Publisher");
        final Button sub = new Button("Subscriber");
        pub.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
                if (t.getSource() == pub) {
                    publish = true;
                    try {
                        initApp("pub");
                    } catch (MqttException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        });
        sub.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
                if (t.getSource() == sub) {
                    subscribe = true;
                    try {
                        initApp("sub");
                    } catch (MqttException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        });
        
        left.getChildren().add(pub);
        right.getChildren().add(sub);
        layout.getChildren().add(left);
        layout.getChildren().add(right);
        layout.setAlignment(left, Pos.CENTER_LEFT);
        layout.setAlignment(left, Pos.CENTER_RIGHT);
        return new Scene(layout, 500, 500);
    }

    private void initApp(String option) throws MqttException, InterruptedException {
        App a = new App();
        a.init(option);
    }

    public void handle(ActionEvent t) {
        if(publish || subscribe){
            System.out.println("dec");
        }
    }

}
