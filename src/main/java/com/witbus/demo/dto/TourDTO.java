package com.witbus.demo.dto;

import java.util.List;

public class TourDTO {
    private  String locationOrigin;
    private String  ticketType;

    public String getLocationOrigin() {
        return locationOrigin;
    }

    public void setLocationOrigin(String locationOrigin) {
        this.locationOrigin = locationOrigin;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }
}
