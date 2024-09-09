package com.Abbas.Online.food.Ordering.Request;

import com.Abbas.Online.food.Ordering.model.Address;
import com.Abbas.Online.food.Ordering.model.ContactInformation;
import lombok.Data;
import java.util.List;

@Data
public class createRestaurantRequest {

    private Long id;

    private String name;

    private String description;

    private String cuisineType; // Changed from 'cuisineTepe' to 'cuisineType'

    private Address address;

    private ContactInformation contactInformation; // Uncomment this line if you need it

    private String openingHours; // Changed from 'opninqHours' to 'openingHours'

    private List<String> images;

    // Getter and Setter for ContactInformation
    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    // Getter and Setter for cuisineType
    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }
}
