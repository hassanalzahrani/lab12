package com.example.execrisespringsecurity.Controller;

import com.example.execrisespringsecurity.Modell.User;
import com.example.execrisespringsecurity.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {




        private final UserService userService;

        @GetMapping("/get")
        public ResponseEntity getAllUser(){
            return ResponseEntity.status(200).body(userService.getAllUsers());
        }

        @PostMapping("/register")
        public ResponseEntity register(@Valid @RequestBody User user) {
            userService.register(user);
            return ResponseEntity.status(200).body("user registered successfully");
        }

        @PutMapping("/update/{user_id}")
        public ResponseEntity updateUser(@PathVariable Integer user_id,@Valid @RequestBody User user) {
            userService.updateUser(user_id,user);
            return ResponseEntity.status(200).body("user updated successfully");
        }
        @DeleteMapping("/delete/{admin_id}/{user_id}")
        public ResponseEntity deleteUser(@PathVariable Integer admin_id,@PathVariable  Integer user_id) {
            userService.deleteUser(admin_id,user_id);
            return ResponseEntity.status(200).body("user deleted successfully");
        }
}
