package com.example.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.DTO.LoginRequest;
import com.example.DTO.SignupRequestDTO;
import com.example.Model.User;
import com.example.Repository.EWalletRepo;

import jakarta.servlet.http.HttpSession;

@Service
public class EWalletServiceImpl implements EWalletService {

    @Autowired
    private EWalletRepo eWalletRepo;

   //  @Override
   // //   public User saveUserInfo(SignupRequestDTO signupRequest){
   // //    String name = signupRequest.getName();
   // //    String email = signupRequest.getEmail();
   // //    String password = signupRequest.getPassword(); 
   // //    Double balance = signupRequest.getBalance();     
   // //      User user = new User(name,email,password,balance);
   // //      session.setAttribute(email, email);
   // //      return eWalletRepo.insert(user);
   // //   }

     @Override
     public User findUserInfo(String id){
         Optional<User> userList = eWalletRepo.findById(id);
         // if(userList.isPresent()){
         //    return userList.get();
         // }
         // else{
         //    return null;
         // }
         return userList.orElse(null);
     }

     @Override
     public String updateUserInfo(String id,String name, String email, String password, Double balance){
         Optional<User> userList = eWalletRepo.findById(id); 
         if(userList.isPresent()){
            User user1 = userList.get();
            if(name!=null){
               user1.setName(name);
            }
            if(email!=null){
               user1.setEmail(email);
            }
            if(password!=null){
               user1.setPassword(password);
            }
            if(balance != null){
               user1.setBalance(balance);
             }
            eWalletRepo.save(user1); 
            return "Updated Successfully";
         }
         else
            return "Record Not Found";
      }

     @Override
     public String deleteUserInfo(String id){
         Optional<User> userList = eWalletRepo.findById(id);
         if(userList.isPresent()){
            eWalletRepo.delete(userList.get());
            return "Deleted Successfully";
         }
         else{
            return "Record Not Found";
         }
     }

   //   @Override
   //   public ResponseEntity<String> loginUserInfo(HttpSession session, LoginRequest loginRequest){
   //       String email = loginRequest.getEmail();
   //       String password = loginRequest.getPassword();
   //       Optional<User> userList = eWalletRepo.findByEmail(email);
   //       if(userList.isPresent()){
   //          if(password.equals(userList.get().getPassword())){
   //             session.setAttribute(email, email);
   //             return new ResponseEntity<>("Login Successful", HttpStatus.OK);
   //          }
   //          else{
   //             return new ResponseEntity<>("Password do not match", HttpStatus.UNAUTHORIZED);
   //          }
   //       }
   //       else{
   //          return new ResponseEntity<>("Email not found", HttpStatus.NOT_FOUND);
   //       }
   //   }

     @Override
     @Transactional
     public ResponseEntity<String> transferAmount(String fromId, Float amount,String toId){
      if(fromId == null)
      {
         return new ResponseEntity<>("User not logged in",HttpStatus.UNAUTHORIZED);
      }
      if(amount<0){
         return new ResponseEntity<>("Inapproriate value for amount",HttpStatus.BAD_REQUEST);
      }
      else{
         Optional<User> userList1 = eWalletRepo.findById(fromId);
         Optional<User> userList2 = eWalletRepo.findById(toId);
         if(userList1.isPresent() && userList2.isPresent()){
            User fromUser = userList1.get();
            User toUser = userList2.get();
            if(fromUser.getBalance()>=amount){
               fromUser.setBalance(fromUser.getBalance()-amount);
               toUser.setBalance(toUser.getBalance()+amount);

               eWalletRepo.save(fromUser);
               eWalletRepo.save(toUser);

               return new ResponseEntity<>("Transaction Successfull",HttpStatus.OK);
            }
            else{
               return new ResponseEntity<>("Insufficient Balance",HttpStatus.BAD_REQUEST);
            }       
         }
         else{
            return new ResponseEntity<>("Record Not Found",HttpStatus.NOT_FOUND);
         }
     }
   }
}    
