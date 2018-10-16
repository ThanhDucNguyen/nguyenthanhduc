package com.witbus.demo.dto;

import com.witbus.demo.dao.models.Seat;
import com.witbus.demo.dao.models.User;

import java.util.Random;

public class BookingDTO {
    private Long id;
    private int tickets;
    private Boolean pay;
    private Integer number ;
    private Integer price;


    @Override
    public String toString() {
        return "BookingDTO{" +
                "id=" + id +
                ", tickets=" + tickets +
                ", pay=" + pay +
                ", number=" + number +
                ", price=" + price +
                ", seat=" + seat +
                ", user=" + user +
                '}';
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    private SeatDTO seat;
    private UserDTO user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    public Boolean getPay() {
        return pay;
    }

    public void setPay(Boolean pay) {
        this.pay = pay;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }


    public SeatDTO getSeat() {
        return seat;
    }

    public void setSeat(SeatDTO seat) {
        this.seat = seat;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
