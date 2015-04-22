package com.goodfood.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Yaroslav on 23.04.2015.
 */

@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @Column(name = "name_ingredients")
    private String nameIngredient;

    private String photo;

    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "type_ingr_to_ingredient",
            joinColumns = {@JoinColumn(name="fk_name_ingr", referencedColumnName = "name_ingredients")},
            inverseJoinColumns = {@JoinColumn(name="fk_type_ingr", referencedColumnName = "name_type")})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<TypeIngredients> typeIngredientsList;

    public String getNameIngredient() {
        return nameIngredient;
    }

    public void setNameIngredient(String nameIngredient) {
        this.nameIngredient = nameIngredient;
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
