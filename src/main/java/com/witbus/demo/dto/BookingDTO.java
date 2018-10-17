package com.witbus.demo.dto;

import java.util.Random;

public class BookingDTO {
    private Long id;
    private Boolean pay;
    private Integer number ;
    private Integer price;
    private String email;
    private String phone;
    private String name;
    private SeatDTO seat;
    private UserDTO user;

    @Override
    public String toString() {
        return "BookingDTO{" +
                "id=" + id +
                ", pay=" + pay +
                ", number=" + number +
                ", price=" + price +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", seat=" + seat +
                ", user=" + user +
                '}';
    }

    public BookingDTO() {
        Random rd = new Random();
        number =  rd.nextInt();
    }


    public Integer getNumber() {
        return number;
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

    public void setNumber(Integer number) {
        this.number = number;
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
