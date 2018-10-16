package com.witbus.demo.dto;

import java.util.List;

public class BusSeatDTO {
    BusDTO bus;
    List<Long> seats;

    public BusDTO getBus() {
        return bus;
    }

    public void setBus(BusDTO bus) {
        this.bus = bus;
    }

    public List<Long> getSeats() {
        return seats;
    }

    public void setSeats(List<Long> seats) {
        this.seats = seats;
    }
}
