package com.example.msa_user_service.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestUser {

    @NotNull(message = "email cannot be null")
    @Size(min = 2, message = "Email not be less than two characters")
    @Email
    private String email;

    @NotNull(message = "Name cannot be null")
    @Size(min = 2,message = "name not be less than two characters")
    private String name;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8,message = "Password must be eqaul or more than 8 characters")
    private String pwd;

}
