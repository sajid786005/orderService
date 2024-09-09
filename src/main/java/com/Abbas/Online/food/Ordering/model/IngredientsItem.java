package com.Abbas.Online.food.Ordering.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientsItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    private  String name;

    @ManyToMany
    private IngredientsItem category;

    @JsonIgnore
    @ManyToOne
    private  boolean inStoke=true;
}
