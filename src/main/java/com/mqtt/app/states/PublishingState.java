/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app.states;

import com.mqtt.app.App;
import com.mqtt.app.Config;
import com.mqtt.app.Controller.PublishController;
import com.mqtt.app.GraphicUserInterface;
import com.mqtt.app.Services.ReplierService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
public class PublishingState {

    TextField values = new TextField("1 3 2 4");
    String operation = new String();
    Label reply = new Label("Reply: ");

    /**
     * This function will set up the publisher module state.
     *
     * @param pane the original pane
     * @return the original pane modified.
     */
    public TilePane setPublisherModule(TilePane pane) {
        pane.getChildren().clear();
        pane.setPrefSize(500, 500);
        pane = setTile(pane);
        App app = new App();
//        app.init("pub", args);
        return pane;
    }

    /**
     * This function is responsible to set the buttons for the pane.
     *
     * @param pane the original pane.
     * @return the original pane modified.
     */
    public TilePane addButtons(TilePane pane) {
        Button sum = new Button("Sum Values");
        Button sub = new Button("Subtract Values");
        Button mul = new Button("Multiply Values");
        Button div = new Button("Divide Values");

        sum = setButtonOperation(sum, "sum");
        sub = setButtonOperation(sub, "sub");
        mul = setButtonOperation(mul, "mul");
        div = setButtonOperation(div, "div");

        pane.getChildren().addAll(sum, sub, mul, div);

        return pane;

    }

    /**
     * This function is responsible to set the pane that will stay at the top.
     *
     * @param pane the original pane.
     * @return the original pane modified.
     */
    private TilePane setTopPane(TilePane pane) {
        TilePane left = new TilePane();
        left.setOrientation(Orientation.HORIZONTAL);
        left.setPrefTileHeight(50);
        left = addButtons(left);
        left = GraphicUserInterface.fillBackground(left, "c7c7c7");

        pane.setOrientation(Orientation.VERTICAL);
        pane.getChildren().add(left);

        return pane;
    }

    /**
     * This function is responsible to set the pane that will stay at the
     * bottom.
     *
     * @param pane the original pane.
     * @return the original pane modified.
     */
    private TilePane setBottomPane(TilePane pane) {
        TilePane right = new TilePane();
        right.setOrientation(Orientation.HORIZONTAL);
        right = GraphicUserInterface.fillBackground(right, "c2c2c2");
        right.setAlignment(Pos.CENTER);
        right.getChildren().addAll(values, reply);
        pane.getChildren().add(right);
        return pane;
    }

    /**
     * This function is responsible for setting the tile pane at the original
     * pane. This means that this method will add the functionalities to the
     * panel.
     *
     * @param pane root pane.
     * @return the original pane modified.
     */
    private TilePane setTile(TilePane pane) {

        pane = setTopPane(pane);
        pane = setBottomPane(pane);

        return pane;
    }

    /**
     * This function is responsible for setting up the click event for the
     * buttons. In this case, it will add the operation functions to them.
     *
     * @param button the button to set up
     * @param operation the operation to set up.
     * @return the original button modified.
     */
    public Button setButtonOperation(Button button, final String operation) {
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
                try {
                    PublishController c = new PublishController(values.getText() + "::" + operation);
                    c.init();
                    Thread.sleep(Config.getTimeout());
                    reply.setText(ReplierService.getReply());
                    if (ReplierService.getReply().length() < 1) {
                        reply.setText("No reply from server, try Again.");
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
