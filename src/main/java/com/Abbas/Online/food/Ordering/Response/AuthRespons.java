package com.Abbas.Online.food.Ordering.Response;

import com.Abbas.Online.food.Ordering.model.USER_ROLE;
import lombok.Data;

@Data
public class AuthRespons {

    private  String jwt;

    private String message;

    private USER_ROLE role;

}
