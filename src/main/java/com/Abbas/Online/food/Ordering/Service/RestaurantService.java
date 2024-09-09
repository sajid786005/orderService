package com.Abbas.Online.food.Ordering.Service;

import com.Abbas.Online.food.Ordering.Request.createRestaurantRequest;
import com.Abbas.Online.food.Ordering.model.Restaurant;
import com.Abbas.Online.food.Ordering.model.User;

import java.util.List;

public interface RestaurantService {

    // Creates a new restaurant
    Restaurant createRestaurant(createRestaurantRequest req, User user);

    // Updates an existing restaurant
    Restaurant updateRestaurant(Long restaurantId, createRestaurantRequest updatedRestaurant) throws Exception;

    // Retrieves all restaurants
    List<Restaurant> getAllRestaurants();

    // Searches for restaurants based on a keyword
    List<Restaurant> searchRestaurants(String keyword);

    // Finds a restaurant by its ID
    Restaurant findRestaurantById(Long id) throws Exception;

    // Retrieves a restaurant associated with a specific user ID
    Restaurant getRestaurantByUserId(Long userId) throws Exception;

    // Adds or removes a restaurant from the user's favorites
    Restaurant addToFavorites(Long restaurantId, User user) throws Exception;

    // Updates the status of a restaurant (e.g., open or closed)
    Restaurant updateRestaurantStatus(Long id, boolean status) throws Exception;

    // Deletes a restaurant
    void deleteRestaurant(Long id, User user) throws Exception;
}
