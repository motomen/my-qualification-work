package com.goodfood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Yaroslav on 23.02.2015.
 */

@Entity
@Table(name ="rating")
public class Rating {

    @Id
    @Column(name = "id_rating")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idRating;

    @Column(name = "value")
    private double value;

    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_food_tc")
    @JsonIgnore
    private Food food;

    public int getIdRating() {
        return idRating;
    }

    public void setIdRating(int idRating) {
        this.idRating = idRating;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
