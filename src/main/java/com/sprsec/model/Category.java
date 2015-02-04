package com.sprsec.model;


import javax.persistence.*;
import java.io.Serializable;
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
    private String photo;

    @OneToMany(mappedBy = "category")
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<Subcategory> getSubCategories() {
        return subCategories;
    }

//    public void setSubCategories(List<SubCategory> subCategories) {
//        this.subCategories = subCategories;
//    }


}
