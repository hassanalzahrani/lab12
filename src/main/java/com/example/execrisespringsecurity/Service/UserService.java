package com.example.execrisespringsecurity.Service;

import com.example.execrisespringsecurity.API.ApiException;
import com.example.execrisespringsecurity.Modell.User;
import com.example.execrisespringsecurity.REpository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void register(User user) {

        user.setRole("ADMIN");
//        user.setRole("USER");



        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        userRepository.save(user);

    }

    public void updateUser(Integer user_id, User user) {
        User user1 = userRepository.findUserById(user_id);
        if (user1 == null) {
            throw new ApiException("User not found");
        }

        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user1.setPassword(hash);
        // user1.setPassword(user.getPassword());
        user1.setUsername(user.getUsername());
        userRepository.save(user1);


    }


    public void deleteUser(Integer admin_id, Integer user_id) {
        User user1 = userRepository.findUserById(admin_id);
        User user2 = userRepository.findUserById(user_id);
        if (user1 == null || user2 == null) {
            throw new ApiException("User not found");
        }

        userRepository.delete(user2);


    }
}
