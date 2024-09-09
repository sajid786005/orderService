package com.Abbas.Online.food.Ordering.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private USER_ROLE role;
    private String fullName; // Added this field

    @ManyToMany
    @JoinTable(
            name = "user_favorites",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_id")
    )
    private Set<Restaurant> favorites = new HashSet<>();

    // No-argument constructor
    public User() {
    }

    // Parameterized constructor
    public User(Long id, String email, String password, USER_ROLE role, String fullName) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.fullName = fullName;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public USER_ROLE getRole() {
        return role;
    }

    public void setRole(USER_ROLE role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName; // Added getter
    }

    public void setFullName(String fullName) {
        this.fullName = fullName; // Added setter
    }

    public Set<Restaurant> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<Restaurant> favorites) {
        this.favorites = favorites;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", fullName='" + fullName + '\'' + // Included fullName
                '}';
    }
}
