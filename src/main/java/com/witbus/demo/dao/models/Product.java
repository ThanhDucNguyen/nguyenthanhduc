package com.witbus.demo.dao.models;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "product")
public class Product  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private String price;

    @Column(name = "color")
    private String color;

    @Column(name = "origin")
    private String origin;

    @Column(name = "mainten")
    private Integer mainten;

    @Column(name = "image")
    private String image;

    @Column(name = "wood_type")
    private String woodType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Integer getMainten() {
        return mainten;
    }

    public void setMainten(Integer mainten) {
        this.mainten = mainten;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWoodType() {
        return woodType;
    }

    public void setWoodType(String woodType) {
        this.woodType = woodType;
    }

}
