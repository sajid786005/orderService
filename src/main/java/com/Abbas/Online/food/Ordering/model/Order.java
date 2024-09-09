package com.Abbas.Online.food.Ordering.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name = "orders")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private  Restaurant restaurant;

    private  String orderStatus;


    private String createdAt;

    @ManyToOne
    @JoinColumn
    private  Address deliveryAddress;

    @ManyToOne
    @JoinColumn
    private List<OrderItem> items;

    @Column(nullable = false)
    private int totalItem;

    @Column(nullable = false)
    private int totalPrice;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

}
