package com.witbus.demo.dto;

import java.util.List;

public class BusOwnerDTO {
    private Long id;
    private String name;
    private List<BusDTO> bus;

    @Override
    public String toString() {
        return "BusOwnerDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bus=" + bus +
                '}';
    }

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

    public List<BusDTO> getBus() {
        return bus;
    }

    public void setBus(List<BusDTO> bus) {
        this.bus = bus;
    }
}
