package com.example.Service;

import org.springframework.http.ResponseEntity;

import com.example.DTO.LoginRequest;
import com.example.DTO.SignupRequestDTO;
import com.example.Model.User;

import jakarta.servlet.http.HttpSession;

public interface EWalletService {
    // public User saveUserInfo(HttpSession session,SignupRequestDTO signupRequest);

    public User findUserInfo(String id);

    public String updateUserInfo(String id,String name, String email, String password, Double balance);

    public String deleteUserInfo(String id);

    // public ResponseEntity<String> loginUserInfo(HttpSession session,LoginRequest loginRequest);

    public ResponseEntity<String> transferAmount(String fromId, Float amount, String toId);
}
