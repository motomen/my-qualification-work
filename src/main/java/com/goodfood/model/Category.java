package com.goodfood.model;


import org.codehaus.jackson.annotate.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.util.List;

/**
 * Created by Yaroslav on 03.02.2015.
 */
@Entity
@Table(name="categorys")
public class Category implements Serializable{

    @Id
    @Column(name = "id_category")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idCategory;
    private String name;
    private String description;

    @Lob
    private Blob photo;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Subcategory> subCategories;

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Blob getPhoto() {
        return photo;
    }

    public void setPhoto(Blob photo) {
        this.photo = photo;
    }

    public List<Subcategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Subcategory> subCategories) {
        this.subCategories = subCategories;
    }


}
