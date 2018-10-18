package com.witbus.demo.dao.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "bus")
public class Bus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus_id")
    private Long id;

    @Column(name = "bus_plate")
    private String plate;

    @Column(name = "bus_name")
    private String name;

    @Column(name = "bus_origin")
    private String origin;

    @Column(name = "bus_destination")
    private String destination;

    @Column(name = "bus_date")
    private String date;

    @Column(name = "bus_start_time")
    private String startTime;

    @Column(name = "bus_end_time")
    private String endTime;

    @Column(name = "bus_distance_time")
    private Integer distanceTime;

    @Column(name = "bus_price_default")
    private Integer priceDefault;

    @ManyToOne
    @JoinColumn(name = "bus_owner_id")
    private BusOwner busOwner;

    @OneToMany(mappedBy = "bus", fetch = FetchType.EAGER)
    private Set<Seat> seats;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public BusOwner getBusOwner() {
        return busOwner;
    }

    public void setBusOwner(BusOwner busOwner) {
        this.busOwner = busOwner;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }
}
