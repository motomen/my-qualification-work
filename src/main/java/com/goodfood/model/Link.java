package com.goodfood.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Yaroslav on 22.04.2015.
 */

@Entity
@Table(name = "link")
public class Link {

    @Id
    @Column(name = "address")
    private String address;

    @Column(name = "name")
    private String name;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
