package com.example.execrisespringsecurity.REpository;

import com.example.execrisespringsecurity.Modell.Blog;
import com.example.execrisespringsecurity.Modell.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {


    Blog findBlogById(Integer id);

    List<Blog> findAllByUser(User user);

    Blog findBlogByUser(User user);

Blog findBlogByTitle(String title);
}
