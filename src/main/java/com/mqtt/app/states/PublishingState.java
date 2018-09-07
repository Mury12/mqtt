/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app.states;

import com.mqtt.app.states.interfaces.StateInterface;
import com.mqtt.app.App;
import com.mqtt.app.Config;
import com.mqtt.app.controller.PublishController;
import com.mqtt.app.generators.GUI;
import com.mqtt.app.generators.GuiSettings;
import com.mqtt.app.services.ReplierService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 *
 * @author andremury
 */
public class PublishingState implements StateInterface {

    TextField values = new TextField("1 3 2 4");
    String operation = new String();
    Label reply = new Label("Reply: ");

    public TilePane setModule(TilePane pane) {
        pane.getChildren().clear();
        pane.setPrefSize(
                GuiSettings.getWidth(),
                GuiSettings.getHeight()
        );
        pane.setPrefTileHeight(GuiSettings.getTileHeight(2));
        pane = setTile(pane);
        App app = new App();
        return pane;
    }

    public TilePane addButtons(TilePane pane) {
        Button sum = new Button("Sum Values");
        Button sub = new Button("Subtract Values");
        Button mul = new Button("Multiply Values");
        Button div = new Button("Divide Values");
        Button back = new Button("Back");

        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                GUI.getMenu();
            }
        });
        sum = setButtonOperation(sum, "sum");
        sub = setButtonOperation(sub, "sub");
        mul = setButtonOperation(mul, "mul");
        div = setButtonOperation(div, "div");

        pane.getChildren().addAll(sum, sub, mul, div, back);

        return pane;

    }

    public TilePane setTopPane(TilePane pane) {
        TilePane left = new TilePane();
        left.setOrientation(Orientation.HORIZONTAL);
        left.setAlignment(Pos.CENTER);
        left.setPrefTileHeight(GuiSettings.getTileHeight(2));
        left = addButtons(left);
        left = GUI.fillBackground(left, "c7c7c7");

        pane.setOrientation(Orientation.VERTICAL);
        pane.getChildren().add(left);

        return pane;
    }

    public TilePane setBottomPane(TilePane pane) {
        TilePane right = new TilePane();
        right.setOrientation(Orientation.HORIZONTAL);
        right = GUI.fillBackground(right, "c2c2c2");
        right.setAlignment(Pos.CENTER);
        right.getChildren().addAll(values, reply);
        pane.getChildren().add(right);
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
                    if (values.getText().length() > 0) {
                        PublishController c = new PublishController(values.getText() + "::" + operation);
                        c.init();
                        Thread.sleep(Config.getTimeout());
                        reply.setText(ReplierService.getReply());
                        if (ReplierService.getReply().length() < 1) {
                            reply.setText("No reply from server, try Again.");
                        }
                    } else {
                        reply.setText("Please insert at least one value.");
                    }
                    ReplierService.resetReply();
                } catch (MqttException ex) {
                    Logger.getLogger(PublishingState.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PublishingState.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return button;
    }

}
