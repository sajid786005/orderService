package com.Abbas.Online.food.Ordering.Repository;

import com.Abbas.Online.food.Ordering.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
