package com.goodfood.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by Yaroslav on 01.02.2015.
 */

@Entity
@Table(name = "food")
public class Food{

    @Id
    @Column(name = "id_food_tc")
    private String idFood;
    private String name;
    private String photo;
    private Double rating;
    private Double protein;
    private Double fats;
    private Double carbs;
    private Double kcal;
    private String ingredients;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "food_to_category",
            joinColumns = {@JoinColumn(name="id_fk_food", referencedColumnName = "id_food_tc")},
            inverseJoinColumns = {@JoinColumn(name="id_fk_fcategory", referencedColumnName = "id_sub_category")}
    )
//    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Subcategory> subcategories;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "comment_to_food",
            joinColumns = {@JoinColumn(name="fk_food", referencedColumnName = "id_food_tc")},
            inverseJoinColumns = {@JoinColumn(name="fk_idcomment", referencedColumnName = "id_comment")})
    private List<Comments> commentsList;

    public String getIdFood() {
        return idFood;
    }

    public void setIdFood(String idFood) {
        this.idFood = idFood;
    }

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

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public Double getFats() {
        return fats;
    }

    public void setFats(Double fats) {
        this.fats = fats;
    }

    public Double getCarbs() {
        return carbs;
    }

    public void setCarbs(Double carbs) {
        this.carbs = carbs;
    }

    public Double getKcal() {
        return kcal;
    }

    public void setKcal(Double kcal) {
        this.kcal = kcal;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public Set<Subcategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(Set<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }

    public List<Comments> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<Comments> commentsList) {
        this.commentsList = commentsList;
    }
}