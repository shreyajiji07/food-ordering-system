package com.foodordering.service;

import com.foodordering.model.FoodItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodService {

    private final List<FoodItem> foods = new ArrayList<>();

    // Add sample food items when the application starts
    public FoodService() {
        foods.add(new FoodItem(1L, "Burger", 120.0,
                "Fast Food", true));
        foods.add(new FoodItem(2L, "Pizza", 250.0,
                "Fast Food", true));
        foods.add(new FoodItem(3L, "Dosa", 80.0,
                "South Indian", true));
        foods.add(new FoodItem(4L, "Fried Rice", 150.0,
                "Chinese", true));
    }

    // Get the complete menu
    public List<FoodItem> getAllFoods() {
        return foods;
    }

    // Find a food item using its ID
    public FoodItem getFoodById(Long id) {
        return foods.stream()
                .filter(food -> food.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Search food items by name
    public List<FoodItem> searchFoods(String name) {
        return foods.stream()
                .filter(food -> food.getName()
                        .toLowerCase()
                        .contains(name.toLowerCase()))
                .toList();
    }

    // Add a new food item
    public FoodItem addFood(FoodItem food) {
        long nextId = foods.stream()
                .mapToLong(FoodItem::getId)
                .max()
                .orElse(0) + 1;

        food.setId(nextId);
        foods.add(food);
        return food;
    }
}
