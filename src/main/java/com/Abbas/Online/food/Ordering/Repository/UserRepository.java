package com.Abbas.Online.food.Ordering.Repository;

import com.Abbas.Online.food.Ordering.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
