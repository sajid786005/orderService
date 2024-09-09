package com.Abbas.Online.food.Ordering.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class ContactInformation {
    private String phoneNumber;
    private String email;
    private String faxNumber; // Add other contact fields as needed

    // You can add validation annotations here if required
}
