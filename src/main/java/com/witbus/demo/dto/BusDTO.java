package com.witbus.demo.dto;

import java.util.List;

public class BusDTO {
    private Long id;
    private String plate;
    private String name;
    private String origin;
    private String destination;
    private String startTime;
    private String endTime;
    private String date;
    private Integer distanceTime;
    private Integer priceDefault;
    private BusOwnerDTO busOwner;
    private List<SeatDTO> seats;

    @Override
    public String toString() {
        return "BusDTO{" +
                "id=" + id +
                ", plate='" + plate + '\'' +
                ", name='" + name + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", date='" + date + '\'' +
                ", distanceTime=" + distanceTime +
                ", priceDefault=" + priceDefault +
                ", busOwner=" + busOwner +
                ", seats=" + seats +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getDistanceTime() {
        return distanceTime;
    }

    public void setDistanceTime(Integer distanceTime) {
        this.distanceTime = distanceTime;
    }

    public Integer getPriceDefault() {
        return priceDefault;
    }

    public void setPriceDefault(Integer priceDefault) {
        this.priceDefault = priceDefault;
    }

    public BusOwnerDTO getBusOwner() {
        return busOwner;
    }

    public void setBusOwner(BusOwnerDTO busOwner) {
        this.busOwner = busOwner;
    }

    public List<SeatDTO> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatDTO> seats) {
        this.seats = seats;
    }
}
