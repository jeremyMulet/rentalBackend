package com.chatop.rentalbackend.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Jérémy MULET on 16/08/2023.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstname;

    private String lastname;

    private String email;

    private  String password;

}
