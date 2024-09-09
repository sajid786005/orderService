package com.Abbas.Online.food.Ordering.Repository;

import com.Abbas.Online.food.Ordering.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant ,  Long> {


    @Query("SELECT r FROM Restaurant r WHERE lower (r.name) LIKE lower(concat('%',:query,'%') ORlower(r.cusineType))"+
            "or lower(r.cuisineType) LIKE lower(concat('%',:qure,'%'))")
    List<Restaurant> findBySerchQuery(String query);


    Restaurant findByOwnerId(Long userid);


    List<Restaurant> findBySearchQuery(String keyword);
}
