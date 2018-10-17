package com.witbus.demo.dao.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "offer")
public class Offer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offer_id")
    private Long id;

    @Column(name = "offer_name")
    private String name;

    @Column(name = "offer_price")
    private Integer price;

    @Column(name = "offer_info")
    private String info;

    @Column(name = "offer_code")
    private String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
