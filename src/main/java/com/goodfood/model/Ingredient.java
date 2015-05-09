package com.goodfood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "bad")
    private boolean bad;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "type_ingr_to_ingredient",
            joinColumns = {@JoinColumn(name="fk_name_ingr", referencedColumnName = "name_ingredients")},
            inverseJoinColumns = {@JoinColumn(name="fk_type_ingr", referencedColumnName = "name_type")})
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<TypeIngredients> typeIngredientsList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "ingredient_to_link",
            joinColumns = {@JoinColumn(name="fk_name_ingr", referencedColumnName = "name_ingredients")},
            inverseJoinColumns = {@JoinColumn(name="fk_address", referencedColumnName = "address")})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Link> linkList;

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

    public List<TypeIngredients> getTypeIngredientsList() {
        return typeIngredientsList;
    }

    public void setTypeIngredientsList(List<TypeIngredients> typeIngredientsList) {
        this.typeIngredientsList = typeIngredientsList;
    }

    public List<Link> getLinkList() {
        return linkList;
    }

    public void setLinkList(List<Link> linkList) {
        this.linkList = linkList;
    }

    public boolean isBad() {
        return bad;
    }

    public void setBad(boolean bad) {
        this.bad = bad;
    }
}
