package com.sprsec.model;

import org.codehaus.jackson.annotate.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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
    @JsonBackReference
    private Category category;

    @OneToMany
    @JoinTable(
            name = "food_to_category",
            joinColumns = {@JoinColumn(name="id_fk_fcategory", referencedColumnName = "id_sub_category")},
            inverseJoinColumns = {@JoinColumn(name="id_fk_food", referencedColumnName = "id_food_tc")}
    )

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
}
