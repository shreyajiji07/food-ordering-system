package com.foodordering.controller;

import com.foodordering.model.FoodItem;
import com.foodordering.service.FoodService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    // 1. Get all food items
    @GetMapping
    public List<FoodItem> getAllFoods() {
        return foodService.getAllFoods();
    }

    // 2. Get a food item by ID
    @GetMapping("/{id}")
    public ResponseEntity<FoodItem> getFoodById(
            @PathVariable Long id) {

        FoodItem food = foodService.getFoodById(id);

        if (food == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(food);
    }

    // 3. Search food items by name
    @GetMapping("/search")
    public List<FoodItem> searchFoods(
            @RequestParam String name) {

        return foodService.searchFoods(name);
    }

    // 4. Add a new food item
    @PostMapping
    public ResponseEntity<FoodItem> addFood(
            @RequestBody FoodItem food) {

        if (food.getName() == null
                || food.getName().isBlank()
                || food.getPrice() <= 0
                || food.getCategory() == null
                || food.getCategory().isBlank()) {

            return ResponseEntity.badRequest().build();
        }

        FoodItem savedFood = foodService.addFood(food);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedFood);
    }
}
