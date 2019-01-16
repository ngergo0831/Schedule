package com.example.ngergo.schedule;

public class BusNumber {
    private String routeNumber;
    private String name;
    private String bus_back;

    public String getBus_back() {
        return bus_back;
    }

    public void setBus_back(String bus_back) {
        this.bus_back = bus_back;
    }

    BusNumber(String name, String routeName, String bus_back)
    {
        this.routeNumber=routeName;
        this.name=name;
        this.bus_back=bus_back;
    }

    BusNumber()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRouteNumber() {

        return routeNumber;
    }

    public void setRouteNumber(String routeNumber) {
        this.routeNumber = routeNumber;
    }
}
