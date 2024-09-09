package com.Abbas.Online.food.Ordering.dto;

import lombok.Data;
import java.util.List;

@Data
public class REstaurantDto {

    private String title;
    private List<String> images;
    private String description;
    private Long id;
}
