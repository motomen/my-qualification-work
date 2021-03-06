package com.goodfood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by Yaroslav on 04.02.2015.
 */
@Entity
@Table(name = "sub_category")
public class Subcategory implements Serializable {

    @Id
    @Column(name = "id_sub_category")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idSubCategory;
    private String name;

    private String img;

    @ManyToOne
    @JoinColumn(name = "fk_category")
   // @JsonBackReference
    @JsonIgnore
    private Category category;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "food_to_category",
            joinColumns = {@JoinColumn(name="id_fk_fcategory", referencedColumnName = "id_sub_category")},
            inverseJoinColumns = {@JoinColumn(name="id_fk_food", referencedColumnName = "id_food_tc")}

    )
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Food> food;

    public int getIdSubCategory() {
        return idSubCategory;
    }

    public void setIdSubCategory(int idSubCategory) {
        this.idSubCategory = idSubCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Food> getFood() {
        return food;
    }

    public void setFood(List<Food> food) {
        this.food = food;
    }
}
