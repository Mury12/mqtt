/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app.states;

import com.mqtt.app.controller.SubscribeController;
import com.mqtt.app.generators.GUI;
import com.mqtt.app.generators.GuiSettings;
import com.mqtt.app.services.ReplierService;
import com.mqtt.app.states.interfaces.StateInterface;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 *
 * @author andremury
 */
public class SubscriberState implements StateInterface {

    Label stat = new Label("Connect to start receiving messages.");
    static Label reply = new Label("...");
    String op = new String();
    SubscribeController stc;

    public TilePane getState() {
        TilePane layout = new TilePane();
        return setModule(layout);
    }

    public TilePane setModule(TilePane pane) {
        try {
            stc = new SubscribeController();
            pane.getChildren().clear();
            pane.setPrefSize(
                    GuiSettings.getWidth(),
                    GuiSettings.getHeight()
            );
            
            pane = setTile(pane);
        } catch (MqttException ex) {
            Logger.getLogger(SubscriberState.class.getName()).log(Level.SEVERE, null, ex);
        }
            return pane;
    }

    public TilePane addButtons(TilePane pane) {
        Button conn = new Button("Connect");
        Button disc = new Button("Disconnect");
        Button back = new Button("Back");

        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    stc.init("out");
                } catch (MqttException ex) {
                    Logger.getLogger(SubscriberState.class.getName()).log(Level.SEVERE, null, ex);
                }
                GUI.getMenu();
            }
        });

        conn = this.setButtonOperation(conn, "in");
        disc = this.setButtonOperation(disc, "out");
        pane.getChildren().addAll(conn, disc, back);
        return pane;
    }

    public TilePane setTopPane(TilePane pane) {
        TilePane top = new TilePane();

        top.setOrientation(Orientation.HORIZONTAL);

        top.setPrefWidth(
                GuiSettings.getWidth()
        );

        top.setPrefTileHeight(GuiSettings.getTileHeight(2));
        top.setAlignment(Pos.CENTER);
        top = addButtons(top);
        top = GUI.fillBackground(top, "c7c7c7");

        pane.setOrientation(Orientation.HORIZONTAL);
        pane.getChildren().add(top);

        return pane;
    }

    public TilePane setBottomPane(TilePane pane) {
        TilePane bottom = new TilePane();

        bottom.setOrientation(Orientation.VERTICAL);
        bottom = GUI.fillBackground(bottom, "c2c2c2");
        bottom.setAlignment(Pos.CENTER);
        bottom.getChildren().addAll(stat, reply);

        pane.getChildren().add(bottom);

        return pane;
    }

    public TilePane setTile(TilePane pane) {

        pane = setTopPane(pane);
        pane = setBottomPane(pane);

        return pane;
    }

    public Button setButtonOperation(Button button, final String operation) {
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
                try {
                    stc.init(operation);
                } catch (MqttException ex) {
                    Logger.getLogger(SubscriberState.class.getName()).log(Level.SEVERE, null, ex);
                }
                stat.setText(stc.getRepĺy());
            }
        });
        return button;
    }

    public static void setReply() {
        reply.setText(ReplierService.getReply());
    }

}
