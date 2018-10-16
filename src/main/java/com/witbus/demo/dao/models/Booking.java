package com.witbus.demo.dao.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Random;

@Entity
@Table(name = "booking")
public class Booking implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long id;

    @Column(name = "booking_tickets")
    private int tickets;

    @Column(name = "booking_ispaid")
    private Boolean pay;

    @Column(name = "booking_number")
    private Integer number ;

    public Booking() {
        Random rd = new Random();
        number =  rd.nextInt();
    }

    @Column(name = "booking_date")
    @Temporal(TemporalType.DATE)
    private Date date = new Date();

    @Column(name = "booking_price")
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
