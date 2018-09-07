/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app.states;

import com.mqtt.app.App;
import com.mqtt.app.generators.GUI;
import static com.mqtt.app.generators.GUI.fillBackground;
import com.mqtt.app.generators.GuiSettings;
import com.mqtt.app.states.interfaces.StateInterface;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 *
 * @author andremury
 */
public class MenuState implements StateInterface {

    boolean publish = false,
            subscribe = false;

    /**
     * Defines an initial pane.
     *
     * @return the scene.
     */
    public TilePane getMenuState() {
        final TilePane layout = new TilePane();
        TilePane left = new TilePane();
        final Button pub = new Button("Publisher");
        final Button sub = new Button("Subscriber");
        final Button conf = new Button("Settings");

        left.setOrientation(Orientation.VERTICAL);
        left.setPrefTileHeight(GuiSettings.getTileHeight(5));
        left.setPrefTileWidth(GuiSettings.getWidth());

        left = fillBackground(left, "c7c7c7");
        left.getChildren().addAll(pub, sub, conf);

        layout.setOrientation(Orientation.VERTICAL);
        layout.getChildren().addAll(left);

        conf.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                SettingsState s = new SettingsState();
                s.setModule(layout);
            }
        });
        pub.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
                publish = true;
                PublishingState pubs = new PublishingState();
                pubs.setModule(layout);
            }

        });
        sub.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
                subscribe = true;
                SubscriberState subs = new SubscriberState();
                subs.setModule(layout);
            }
        });

        return layout;
    }

    public TilePane setModule(TilePane pane) {
        pane.getChildren().clear();
        
        pane.setPrefSize(
                GuiSettings.getWidth(),
                GuiSettings.getHeight()
        );
        
        pane = setTile(pane);
        return pane;
    }

    public TilePane addButtons(TilePane pane) {

        return pane;
    }

    public TilePane setTopPane(TilePane pane) {
        return pane;
    }

    public TilePane setBottomPane(TilePane pane) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public TilePane setTile(TilePane pane) {
        pane = getMenuState();
        return pane;
    }

    public Button setButtonOperation(Button button, String operation) {
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        return button;
    }
}
