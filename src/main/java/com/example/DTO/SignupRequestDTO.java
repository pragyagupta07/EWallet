package com.example.DTO;


import lombok.Data;

@Data
public class SignupRequestDTO {
    private String name;
    private String email;
    private double balance;
    private String password;
}
