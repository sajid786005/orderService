package com.Abbas.Online.food.Ordering.Controller;

import com.Abbas.Online.food.Ordering.Response.MessageResponse;
import com.Abbas.Online.food.Ordering.Service.RestaurantService;
import com.Abbas.Online.food.Ordering.Service.UserService;
import com.Abbas.Online.food.Ordering.Request.createRestaurantRequest;
import com.Abbas.Online.food.Ordering.model.Restaurant;
import com.Abbas.Online.food.Ordering.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(
            @RequestBody createRestaurantRequest request,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.createRestaurant(request, user);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(
            @PathVariable Long id,
            @RequestBody createRestaurantRequest request,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.updateRestaurant(id, request);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteRestaurant(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        restaurantService.deleteRestaurant(id, user);
        MessageResponse response = new MessageResponse();
        response.setMessage("Restaurant deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Restaurant> updateRestaurantStatus(
            @PathVariable Long id,
            @RequestParam boolean status,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.updateRestaurantStatus(id, status);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Restaurant> findRestaurantByUserId(
            @PathVariable Long userId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }
}
