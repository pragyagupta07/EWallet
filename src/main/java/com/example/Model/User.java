package com.example.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {

    @Id
    private String id;

    private String name;

    private String email;

    private String password;

    private Double balance;

    public User(String Name, String Email, String Password, Double Balance)
    {
        this.name = Name;
        this.email = Email;
        this.password = Password;
        this.balance = Balance;
    }


}
