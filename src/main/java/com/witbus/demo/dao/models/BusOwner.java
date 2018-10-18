package com.witbus.demo.dao.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "bus_owner")
public class BusOwner implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus_owner_id")
    private Long id;

    @Column(name = "bus_owner_name")
    private String name;

    @OneToMany(mappedBy = "busOwner", fetch = FetchType.EAGER)
    private Set<Bus> bus;


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

    public Set<Bus> getBus() {
        return bus;
    }

    public void setBus(Set<Bus> bus) {
        this.bus = bus;
    }
}
