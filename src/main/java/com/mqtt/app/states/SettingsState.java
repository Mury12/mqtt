/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mqtt.app.states;

import com.mqtt.app.Config;
import com.mqtt.app.controller.SettingsController;
import com.mqtt.app.generators.GUI;
import com.mqtt.app.generators.GuiSettings;
import com.mqtt.app.states.interfaces.StateInterface;
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

/**
 *
 * @author andremury
 */
public class SettingsState implements StateInterface {

    TextField txtf = new TextField(Config.getHost());
    Label reply = new Label("Type an IP then change it.");
    String op = new String();

    /**
     * This function is responsible for getting the state contained.
     * @return 
     */
    public TilePane getState() {
        TilePane layout = new TilePane();
        return setModule(layout);
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
        Button host = new Button("Change Host");
        Button topic = new Button("Change Topic");
        Button dft = new Button("Restore default");
        Button back = new Button("Back");

        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                GUI.getMenu();
            }
        });

        host = this.setButtonOperation(host, "changeHost");
        topic = this.setButtonOperation(topic, "changeTopic");
        dft = this.setButtonOperation(dft, "restDefaults");
        pane.getChildren().addAll(host, topic, dft, back);
        return pane;
    }

    public TilePane setTopPane(TilePane pane) {
        TilePane top = new TilePane();

        top.setOrientation(Orientation.HORIZONTAL);
        top.setPrefTileHeight(GuiSettings.getTileHeight(2));
        top.setAlignment(Pos.CENTER);
        top = addButtons(top);
        top = GUI.fillBackground(top, "c7c7c7");

        pane.setOrientation(Orientation.VERTICAL);
        pane.getChildren().add(top);

        return pane;
    }

    public TilePane setBottomPane(TilePane pane) {
        TilePane bottom = new TilePane();

        bottom.setOrientation(Orientation.HORIZONTAL);
        bottom = GUI.fillBackground(bottom, "c2c2c2");
        bottom.setAlignment(Pos.CENTER);
        bottom.getChildren().addAll(txtf, reply);

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
                SettingsController stc = new SettingsController(txtf.getText() + "::" + operation);
                stc.init();
                try {
                    Thread.sleep(Config.getTimeout());
                } catch (InterruptedException ex) {
                    Logger.getLogger(SettingsState.class.getName()).log(Level.SEVERE, null, ex);
                }
                reply.setText(stc.getRepĺy());
                updateComponents();
            }
        });
        return button;
    }

    private void updateComponents() {
        this.txtf.setText(Config.getHost());
    }

}
