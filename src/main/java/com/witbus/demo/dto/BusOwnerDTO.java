package com.witbus.demo.dto;

import java.util.List;
import java.util.Random;

public class BusOwnerDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Integer numberPass;
    public BusOwnerDTO(){
        int min = 1;
        int max = 999;
        Random rd = new Random();
        numberPass = min + rd.nextInt(max - min);
    }
    private List<BusDTO> bus;

    @Override
    public String toString() {
        return "BusOwnerDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", numberPass=" + numberPass +
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getNumberPass() {
        return numberPass;
    }

    public void setNumberPass(Integer numberPass) {
        this.numberPass = numberPass;
    }
}
