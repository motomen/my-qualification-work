package com.goodfood.service.impl;

import com.goodfood.dao.FoodDAO;
import com.goodfood.dao.IngredientDao;
import com.goodfood.model.Food;
import com.goodfood.model.Ingredient;
import com.goodfood.model.Subcategory;
import com.goodfood.model.TypeIngredients;
import com.goodfood.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yaroslav on 01.02.2015.
 */
@Service
@Transactional
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodDAO foodDAO;

    @Autowired
    private IngredientDao ingredientDao;

    @Override
    public void addFood(Food food) {
        String Ingredients = food.getIngredients();
        String notUsedString = "";
        StringBuffer NewIngredients = new StringBuffer();
        String ingredientsForFormula = "";
        String ingredientBefore = "";
        String ingredientAfter = "";
        // get all ingredient for String Ingredients
        // maybe symbol ", . :"
        for (String ingredient : Ingredients.split(",")) {
            // if ingredient contains :
            // example chocolate: cacao
            if (ingredient.contains(":") && !ingredient.contains(".")) {
                notUsedString = ingredient.substring(0, ingredient.lastIndexOf(": ") + 2);
                ingredient = ingredient.replace(notUsedString, "");
                NewIngredients.append(notUsedString);
                NewIngredients.append("<span>");
                NewIngredients.append(ingredient);
                NewIngredients.append(",</span> ");
                ingredientsForFormula += ingredient.toLowerCase() + ","; // make string ingredients for formula
                continue;
            }
            if (ingredient.contains(".") && ingredient.contains(":")) {
                ingredientBefore = ingredient.substring(0, ingredient.lastIndexOf(".") + 1);
                ingredient = ingredient.replace(ingredientBefore, "");
                notUsedString = ingredient.substring(0, ingredient.lastIndexOf(": ") + 1);
                ingredient = ingredient.replace(notUsedString, "");
                ingredientAfter = ingredient.replace(notUsedString, "");
                NewIngredients.append("<span>");
                NewIngredients.append(ingredientBefore);
                NewIngredients.append("</span> ");
                ingredientBefore = ingredientBefore.replace(ingredientBefore, ingredientBefore.substring(1, ingredientBefore.length() - 1));
                ingredientsForFormula += ingredientBefore + ","; // make string ingredients for formula
                NewIngredients.append(notUsedString);
                NewIngredients.append("<span>");
                NewIngredients.append(ingredientAfter);
                NewIngredients.append(",</span> ");
                ingredientAfter = ingredientAfter.replace(ingredientAfter, ingredientAfter.substring(1, ingredientAfter.length()));
                ingredientsForFormula += ingredientAfter + ","; // make string ingredients for formula
                continue;
            }
            if (ingredient.contains(".") && !ingredient.contains(":")) {
                ingredientBefore = ingredient.substring(0, ingredient.lastIndexOf("."));
                ingredient = ingredientBefore.replace(ingredientBefore, ingredientBefore.substring(1, ingredientBefore.length()));
                ingredientsForFormula += ingredient; // make string ingredients for formula
                NewIngredients.append("<span>");
                NewIngredients.append(ingredient);
                NewIngredients.append("</span> ");
                break;
            }
            NewIngredients.append("<span>");
            NewIngredients.append(ingredient);
            NewIngredients.append(",</span> ");
            if (ingredient.charAt(0) == ' ')
                ingredient = ingredient.replace(ingredient, ingredient.substring(1, ingredient.length()));
            ingredientsForFormula += ingredient + ","; // make string ingredients for formula
        }
        food.setIngredients(NewIngredients.toString());
        try {
            food.setRating(getRatingInString(ingredientsForFormula));
        } catch (Exception e) {
            e.printStackTrace();
        }
        foodDAO.addFood(food);
    }

    /**
     * Get rating 1.0 .. 10.0 in parse ingredient string
     *
     * @param strIngredients String example "water,bread,sugar"
     * @return count Rating
     */
    private Double getRatingInString(String strIngredients) throws Exception {

        if (strIngredients.length() == 0) {
            throw new Exception("string ingredients must be not empty");
        }

        if (!strIngredients.contains(",")) { // if list ingredients consist one ingredient
            return 1.0;
        }

        Double rating = 0.0;

        int count = 0;
        int countGood = 0; // count good ingredient

        for (String ingredient : strIngredients.split(",")) {
            if (ingredient.charAt(0) == ' ') {
                ingredient = ingredient.replace(ingredient, ingredient.substring(1, ingredient.length()));
            }

            countGood++;
            Ingredient ingredients = ingredientDao.getIngredientByName(ingredient);

            if (ingredients != null) {
                rating += 0.35;
                if (ingredients.isBad()) {
                    rating += 1.5;
                    count++;
                }
            } else {
                rating += 0.35;
            }

            if (count >= 3) {
                rating += 1.0;
            }

            if (countGood >= 12) {
                rating += 1.0;
            }
        }

        if (rating < 1.0) {
            rating = 1.0;
        }

        if (rating > 10.0) {
            rating = 10.0;
        }

        return Math.floor(rating);
    }

    @Override
    public List<Food> getFood(int count) {
        return foodDAO.getFood(count);
    }

    @Override
    public Food getFoodById(String id) {
        return foodDAO.getFoodById(id);
    }

    @Override
    public List<Food> getAllFood() {
        return foodDAO.getAllFood();
    }

    @Override
    public Food getFoodByName(String name) {
        return foodDAO.getFoodByName(name);
    }

    @Override
    public void update(Food food) {
        String Ingredients = food.getIngredients();
        String notUsedString = "";
        StringBuffer NewIngredients = new StringBuffer();
        String ingredientsForFormula = "";
        String ingredientBefore = "";
        String ingredientAfter = "";

        if (Ingredients.contains("<span>")) {
            Ingredients = Ingredients.replace("<span>", "");
        }

        if (Ingredients.contains("</span> ")) {
            Ingredients = Ingredients.replace("</span> ", "");
        }

        // get all ingredient for String Ingredients
        // maybe symbol ", . :"
        for (String ingredient : Ingredients.split(",")) {
            // if ingredient contains :
            // example chocolate: cacao

            if (ingredient.contains(":") && !ingredient.contains(".")) {
                notUsedString = ingredient.substring(0, ingredient.lastIndexOf(": ") + 2);
                ingredient = ingredient.replace(notUsedString, "");
                NewIngredients.append(notUsedString);
                NewIngredients.append("<span>");
                NewIngredients.append(ingredient);
                NewIngredients.append(",</span> ");
                ingredientsForFormula += ingredient.toLowerCase() + ","; // make string ingredients for formula
                continue;
            }

            if (ingredient.contains(".") && ingredient.contains(":")) {
                ingredientBefore = ingredient.substring(0, ingredient.lastIndexOf(".") + 1);
                ingredient = ingredient.replace(ingredientBefore, "");
                notUsedString = ingredient.substring(0, ingredient.lastIndexOf(": ") + 1);
                ingredient = ingredient.replace(notUsedString, "");
                ingredientAfter = ingredient.replace(notUsedString, "");
                NewIngredients.append("<span>");
                NewIngredients.append(ingredientBefore);
                NewIngredients.append("</span> ");
                ingredientBefore = ingredientBefore.replace(ingredientBefore, ingredientBefore.substring(1, ingredientBefore.length() - 1));
                ingredientsForFormula += ingredientBefore + ","; // make string ingredients for formula
                NewIngredients.append(notUsedString);
                NewIngredients.append("<span>");
                NewIngredients.append(ingredientAfter);
                NewIngredients.append(",</span> ");
                ingredientAfter = ingredientAfter.replace(ingredientAfter, ingredientAfter.substring(1, ingredientAfter.length()));
                ingredientsForFormula += ingredientAfter + ","; // make string ingredients for formula
                continue;
            }

            if (ingredient.contains(".") && !ingredient.contains(":")) {
                ingredientBefore = ingredient.substring(0, ingredient.lastIndexOf("."));
                ingredient = ingredientBefore.replace(ingredientBefore, ingredientBefore.substring(1, ingredientBefore.length()));
                ingredientsForFormula += ingredient; // make string ingredients for formula
                NewIngredients.append("<span>");
                NewIngredients.append(ingredient);
                NewIngredients.append("</span> ");
                break;
            }

            NewIngredients.append("<span>");
            NewIngredients.append(ingredient);
            NewIngredients.append(",</span> ");

            if (ingredient.charAt(0) == ' ') {
                ingredient = ingredient.replace(ingredient, ingredient.substring(1, ingredient.length()));
            }
            ingredientsForFormula += ingredient + ","; // make string ingredients for formula
        }

        food.setIngredients(NewIngredients.toString());

        try {
            food.setRating(getRatingInString(ingredientsForFormula));
        } catch (Exception e) {
            e.printStackTrace();
        }

        foodDAO.update(food);
    }

    @Override
    public List<Food> getAllFoodBySubcategory(int page, int maxResults, Subcategory subcategory) {
        return foodDAO.getAllFoodBySubcategory(page, maxResults, subcategory);
    }

    @Override
    public List<Food> getAllFoodBySubcategory(Subcategory subcategory) {
        return foodDAO.getAllFoodBySubcategory(subcategory);
    }

    @Override
    public List<Food> getAllFoodWithoutSubcategory() {
        return foodDAO.getAllFoodWithoutSubcategory();
    }

    public List<String> getNameFoodForSearch(String name) {
        return foodDAO.getNameFoodForSearch(name);
    }

    @Override
    public List<Food> getFoodForSearch(String name) {
        return foodDAO.getFoodForSearch(name);
    }

    /**
     * get pair name food and count eat food
     *
     * @param count elem map
     * @return
     */
    @Override
    public Map<String, Integer> getMapBestFood(int count) {
        Map<String, Integer> foodMap = new HashMap();
        List<Food> foodList = foodDAO.getBestFoodEats(count);
        for (Food food : foodList) {
            foodMap.put(food.getName(), food.getCalcFoodList().size());
        }
        return foodMap;
    }

    @Override
    public String getStringBestFood(int count) {
        List<Food> foodList = foodDAO.getBestFoodEats(count);
        String result = "";
        for (Food food : foodList) {
            result += "[\'" + food.getName() + "\', " + String.valueOf(food.getCalcFoodList().size()) + "], ";
        }
        result = result.substring(0, result.length() - 2);
        return result;
    }

    @Override
    public void delete(Food food) {
        foodDAO.delete(food);
    }
}
