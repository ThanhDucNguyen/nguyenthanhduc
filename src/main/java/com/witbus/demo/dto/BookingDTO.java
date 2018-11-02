package com.witbus.demo.dto;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Random;

public class BookingDTO {
    private Long id;
    private Boolean pay;
    private Integer number;
    private Integer price;
    private String email;
    private String phone;
    @Temporal(TemporalType.DATE)
    private Date date = new Date();
    private String name;
    private SeatDTO seat;
    private UserDTO user;

    public BookingDTO() {
        int min = 1;
        int max = 9999999;
        Random rd = new Random();
        number = min + rd.nextInt(max - min);
    }
    @Override
    public String toString() {
        return "BookingDTO{" +
                "id=" + id +
                ", pay=" + pay +
                ", number=" + number +
                ", price=" + price +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", date=" + date +
                ", name='" + name + '\'' +
                ", seat=" + seat +
                ", user=" + user +
                '}';
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
