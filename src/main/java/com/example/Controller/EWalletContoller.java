package com.example.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.LoginRequest;
import com.example.DTO.SignupRequestDTO;
import com.example.DTO.TransferRequest;
import com.example.Model.User;
import com.example.Repository.EWalletRepo;
import com.example.Service.EWalletService;
import com.example.Service.EWalletServiceImpl;

import jakarta.servlet.http.HttpSession;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;





@RestController
@RequestMapping("/api")
public class EWalletContoller {

    @Autowired
    private EWalletService eWalletService;
    
    // @PostMapping("/signup")
    // public  User SaveUser(HttpSession session,@RequestBody SignupRequestDTO signupRequestDTO) {
    //     //return ResponseEntity.ok("User registered successfully");
    //     session.getAttributeNames().asIterator().forEachRemaining(attr -> 
    //     System.out.println(attr + ": " + session.getAttribute(attr))
    //     );
    //    return eWalletService.saveUserInfo(session,signupRequestDTO);
    // }

    @GetMapping("/get")
    public User getUser(@RequestParam String id) {
        User userInfo = eWalletService.findUserInfo(id);
        return userInfo;
    }

    @PutMapping("/update")
    public String updateUser(@RequestParam String id, String name, String email, String password, Double balance) {
        return eWalletService.updateUserInfo(id, name, email, password, balance);
      
    }

   @DeleteMapping("/delete")
   public String deleteUser(@RequestParam String id)
   {
        return eWalletService.deleteUserInfo(id);
   }

//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
//        return eWalletService.loginUserInfo(loginRequest);
//    }
   
   @PutMapping("/transfer")
   public ResponseEntity<String> transferAmount(@RequestBody TransferRequest transferRequest) {
       //TODO: process PUT request
       
       return eWalletService.transferAmount(transferRequest.getFromId(),transferRequest.getAmount(),transferRequest.getToId());
   }

//    @PostMapping("/logout")
//     public ResponseEntity<String> logoutUser(HttpSession session) {
//         session.invalidate();
//         return new ResponseEntity<>("Logout Successful", HttpStatus.OK);
//     }

    
    

}
