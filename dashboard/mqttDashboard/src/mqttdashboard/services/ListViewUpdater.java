/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mqttdashboard.services;

import com.mqtt.app.services.ReplierService;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.ListView;
import mqttdashboard.Report;

/**
 *
 * @author andremury
 */
public class ListViewUpdater extends Thread {

    ListView<String> lv;
    ArrayList<Report> arr;
    String reported;
    boolean low, high;
    Double val;
    private String id;
    private String local;
    private String name;
    private Double temp;
    Report rep;

    public ListViewUpdater(ListView lv) {
        this.lv = lv;
        this.arr = new ArrayList<>();
    }

    @Override
    public void run() {
        while(!ReplierService.got()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ListViewUpdater.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        while (true) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(ListViewUpdater.class.getName()).log(Level.SEVERE, null, ex);
            }
            String payback = ReplierService.getReply();
            if(payback.contains("addSensor")){
                continue;
            }
                rep = new Report(getLocation(payback) + "::" + getMachine(payback),
                        getLocation(payback), getMachine(payback), getTemperature(payback));
            
            if (!checkIfExists(arr, rep.getId())) {
                arr.add(rep);
            } else {
                arr = replaceItemOnList(arr, rep);
            }
            Platform.runLater(new Runnable() {

                @Override
                public void run() {
                    lv.getItems().clear();
                    for (Report r : arr) {
                        lv.getItems().add(r.getMessage());
                    }
                }
            });
        }
    }

    public void main(String args[]) {

    }

    public ListView<String> updateListView() {
        this.start();
        return lv;
    }

    public String getLocation(String payback) {
        return payback.split(":")[0];
    }

    public String getMachine(String payback) {
        return payback.split(":")[1];
    }

    public Double getTemperature(String payback) {
        return Double.parseDouble(payback.split(": ")[1]);
    }

    private boolean checkIfExists(ArrayList<Report> arr, String id) {
        int i = 0;

        for (Report r : arr) {
            if (r.getId().contentEquals(id)) {
                return true;
            }
            ++i;
        }
        return false;
    }

    private ArrayList<Report> replaceItemOnList(ArrayList<Report> arr, Report rep) {
        int i = 0;
        for (Report r : arr) {
            if (!r.getId().contentEquals(rep.getId())) {
                arr.get(i).set(rep);
            }
            ++i;
        }
        return arr;
    }


}
