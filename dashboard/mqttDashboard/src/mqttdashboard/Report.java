/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mqttdashboard;

import java.time.Instant;

/**
 *
 * @author andremury
 */
public final class Report {

    private String id;
    private String local;
    private String name;
    private Double temp;
    private Long lastReport;

    public Report(String id, String local, String name, Double temp) {
        this.id = id;
        this.local = local;
        this.name = name;
        this.temp = temp;
        this.lastReport = Instant.now().getEpochSecond();
    }

    public Report() {
    }

    public String getId() {
        return id;
    }

    public String getLocal() {
        return local;
    }

    public String getName() {
        return name;
    }

    public Double getTemp() {
        return temp;
    }

    public String getMessage() {
        return this.name + " at " + this.local + " is " + this.temp + "°C";
    }

    public Long getLastReport() {
        return lastReport;
    }

    public void set(Report rep) {
        this.temp = rep.getTemp();
    }

}
