package com.mqtt.app.states.interfaces;

import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;

/**
 *
 * @author andremury
 */
public interface StateInterface {

    /**
     * This function will set up the publisher module state.
     *
     * @param pane the original pane
     * @return the original pane modified.
     */
    public TilePane setModule(TilePane pane);

    /**
     * This function is responsible to set the buttons for the pane.
     *
     * @param pane the original pane.
     * @return the original pane modified.
     */
    public TilePane addButtons(TilePane pane);

    /**
     * This function is responsible to set the pane that will stay at the top.
     *
     * @param pane the original pane.
     * @return the original pane modified.
     */
    public TilePane setTopPane(TilePane pane);

    /**
     * This function is responsible to set the pane that will stay at the
     * bottom.
     *
     * @param pane the original pane.
     * @return the original pane modified.
     */
    public TilePane setBottomPane(TilePane pane);

    /**
     * This function is responsible for setting the tile pane at the original
     * pane. This means that this method will add the functionalities to the
     * panel.
     *
     * @param pane root pane.
     * @return the original pane modified.
     */
    public TilePane setTile(TilePane pane);

    /**
     * This function is responsible for setting up the click event for the
     * buttons. In this case, it will add the operation functions to them.
     *
     * @param button the button to set up
     * @param operation the operation to set up.
     * @return the original button modified.
     */
    public Button setButtonOperation(Button button, final String operation);

}
