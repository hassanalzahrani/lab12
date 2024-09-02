package com.example.execrisespringsecurity.Service;

import com.example.execrisespringsecurity.API.ApiException;
import com.example.execrisespringsecurity.Modell.User;
import com.example.execrisespringsecurity.REpository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new ApiException("wrong username or password");
        }



        return user ;


    }

}
