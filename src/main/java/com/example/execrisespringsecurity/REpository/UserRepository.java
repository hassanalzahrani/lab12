package com.example.execrisespringsecurity.REpository;

import com.example.execrisespringsecurity.Modell.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(Integer Id);
    User findUserByUsername(String username);
}
