package com.goodfood.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Yaroslav on 22.04.2015.
 */

@Entity
@Table(name = "type_ingredients")
public class TypeIngredients {

    @Id
    @Column(name = "name_type")
    private String name;

    @Column(name = "photo")
    private String photo;

    @Column(name = "description")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
