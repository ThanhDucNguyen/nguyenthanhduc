package com.witbus.demo.dto;

public class ProductDTO {
    private Long id;
    private String type;
    private String name;
    private String price;
    private String color;
    private String origin;
    private Integer mainten;
    private String image;
    private String woodType;

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", color='" + color + '\'' +
                ", origin='" + origin + '\'' +
                ", mainten=" + mainten +
                ", image='" + image + '\'' +
                ", woodType='" + woodType + '\'' +
                '}';
    }

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
