package com.Abbas.Online.food.Ordering.Service;

import com.Abbas.Online.food.Ordering.Config.JwtProvider;
import com.Abbas.Online.food.Ordering.Repository.UserRepository;
import com.Abbas.Online.food.Ordering.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Autowired
    public UserServiceImp(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public User findUserByJwtToken(String jwt) {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        return findUserByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return user;
    }
}
