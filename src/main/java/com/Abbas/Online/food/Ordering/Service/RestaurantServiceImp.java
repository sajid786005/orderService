package com.Abbas.Online.food.Ordering.Service;

import com.Abbas.Online.food.Ordering.Repository.AddressRepository;
import com.Abbas.Online.food.Ordering.Repository.RestaurantRepository;
import com.Abbas.Online.food.Ordering.Repository.UserRepository;
import com.Abbas.Online.food.Ordering.Request.createRestaurantRequest;
import com.Abbas.Online.food.Ordering.model.Address;
import com.Abbas.Online.food.Ordering.model.Restaurant;
import com.Abbas.Online.food.Ordering.model.USER_ROLE;
import com.Abbas.Online.food.Ordering.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImp implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Restaurant createRestaurant(createRestaurantRequest req, User user) {
        Address address = addressRepository.save(req.getAddress());

        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setContactInformation(req.getContactInformation());
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setDescription(req.getDescription());
        restaurant.setImages(req.getImages());
        restaurant.setName(req.getName());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);
        restaurant.setOpen(true); // Assuming a new restaurant is open by default
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, createRestaurantRequest updatedRestaurant) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        // Update only the non-null fields from the updatedRestaurant
        if (updatedRestaurant.getCuisineType() != null) {
            restaurant.setCuisineType(updatedRestaurant.getCuisineType());
        }
        if (updatedRestaurant.getDescription() != null) {
            restaurant.setDescription(updatedRestaurant.getDescription());
        }
        if (updatedRestaurant.getName() != null) {
            restaurant.setName(updatedRestaurant.getName());
        }
        if (updatedRestaurant.getOpeningHours() != null) {
            restaurant.setOpeningHours(updatedRestaurant.getOpeningHours());
        }
        // Add other updates as needed for your fields

        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId, User user) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        // Check if the user is authorized to delete this restaurant
        if (!restaurant.getOwner().equals(user) && !user.getRole().equals(USER_ROLE.ROLE_ADMIN)) {
            throw new RuntimeException("User is not authorized to delete this restaurant");
        }

        restaurantRepository.delete(restaurant);
    }


    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurants(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword); // Adjust based on the actual repository method name
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        if (optionalRestaurant.isEmpty()) {
            throw new Exception("Restaurant not found with id " + id);
        }
        return optionalRestaurant.get();
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {
        Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
        if (restaurant == null) {
            throw new Exception("Restaurant not found with owner id " + userId);
        }
        return restaurant;
    }

    @Override
    public Restaurant addToFavorites(Long restaurantId, User user) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        // Toggle favorite logic
        if (user.getFavorites().contains(restaurant)) {
            user.getFavorites().remove(restaurant);
        } else {
            user.getFavorites().add(restaurant);
        }

        userRepository.save(user);
        return restaurant;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id, boolean status) throws Exception {
        Restaurant restaurant = findRestaurantById(id);
        restaurant.setOpen(status);
        return restaurantRepository.save(restaurant);
    }
}
