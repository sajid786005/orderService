package com.Abbas.Online.food.Ordering.Service;

import com.Abbas.Online.food.Ordering.exception.JwtTokenException;
import com.Abbas.Online.food.Ordering.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

    // Fetches a User by the JWT token; throws JwtTokenException if there's an issue with the token
    User findUserByJwtToken(String jwt) throws JwtTokenException;

    // Fetches a User by email; throws UsernameNotFoundException if the user is not found
    User findUserByEmail(String email) throws UsernameNotFoundException;
}
