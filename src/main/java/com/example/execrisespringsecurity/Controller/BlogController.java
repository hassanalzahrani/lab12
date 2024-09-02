package com.example.execrisespringsecurity.Controller;

import com.example.execrisespringsecurity.API.ApiException;
import com.example.execrisespringsecurity.Modell.Blog;
import com.example.execrisespringsecurity.Modell.User;
import com.example.execrisespringsecurity.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;


    @GetMapping("/get-my")
    public ResponseEntity getMyBlog(@AuthenticationPrincipal User user) {

        return ResponseEntity.status(200).body(blogService.getMyBlog(user.getId()));
    }

    @GetMapping("get-all")
    public ResponseEntity getAllBlog() {
        return ResponseEntity.status(200).body(blogService.getAlLBlog());

    }
    @PostMapping("/add")
    public ResponseEntity addBlog(@AuthenticationPrincipal User user, @Valid @RequestBody Blog blog) {
        blogService.addBlog(user.getId(), blog);
        return ResponseEntity.status(200).body("blog added successfully"+user.getUsername());

    }
    @PutMapping("/update/{blog_id}")
    public ResponseEntity updateBlog(@PathVariable Integer blog_id,@AuthenticationPrincipal User user,@Valid @RequestBody Blog blog) {
        blogService.updateBlog(blog_id, user.getId(), blog);
        return ResponseEntity.status(200).body("blog updated successfully"+user.getUsername());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTodo(@PathVariable Integer id, @AuthenticationPrincipal User user){
        blogService.deleteBlog(id, user.getId());
        return ResponseEntity.status(200).body(new ApiException("blog deleted"));
    }


    @GetMapping("get-by-id/{blog_id}")
    public ResponseEntity getBlogById(@AuthenticationPrincipal User user, @PathVariable Integer blog_id) {
        return ResponseEntity.status(200).body(blogService.getBlogById(user.getId(),blog_id));
    }
    @GetMapping("/get-title/{title}")
    public ResponseEntity getBlogTitle(@AuthenticationPrincipal@PathVariable String title) {
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(title));
    }

}
