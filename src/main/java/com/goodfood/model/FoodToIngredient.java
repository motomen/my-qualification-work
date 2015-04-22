package com.goodfood.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Yaroslav on 23.04.2015.
 */

@Entity
@Table(name = "food_to_ingredients")
public class FoodToIngredient implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "fk_id_food")
    private Food food;

    @Id
    @ManyToOne
    @JoinColumn(name = "fk_name_ingredient")
    private Ingredient ingredient;

    @Column(name = "level_bud")
    private Double levelBud;

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Double getLevelBud() {
        return levelBud;
    }

    public void setLevelBud(Double levelBud) {
        this.levelBud = levelBud;
    }
}
