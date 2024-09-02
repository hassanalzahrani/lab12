package com.example.execrisespringsecurity.Service;

import com.example.execrisespringsecurity.API.ApiException;
import com.example.execrisespringsecurity.Modell.Blog;
import com.example.execrisespringsecurity.Modell.User;
import com.example.execrisespringsecurity.REpository.BlogRepository;
import com.example.execrisespringsecurity.REpository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
private final BlogRepository blogRepository;
private final UserRepository userRepository;


    public List<Blog> getMyBlog(Integer id) {
        User user = userRepository.findUserById(id);

        return blogRepository.findAllByUser(user);


    }
    public List<Blog> getAlLBlog(){
        return blogRepository.findAll();
    }
    public void addBlog(Integer user_id, Blog blog) {
        User user = userRepository.findUserById(user_id);

        blog.setUser(user);
        blogRepository.save(blog);
    }
    public void updateBlog(Integer user_id, Integer todo_id, Blog blog) {
        Blog b=blogRepository.findBlogById(todo_id);
        User u = userRepository.findUserById(user_id);
        if(u==null){
            throw new ApiException("User not found");
        }
        if(b==null){
            throw new ApiException("Blog not found");
        }if (b.getUser().getId() != u.getId()){
                throw new ApiException("User and Blog don't match");
        }
        b.setTitle(blog.getTitle());
        b.setUser(u);
        blogRepository.save(b);


    }

    public void deleteBlog(Integer id, Integer user_id){
        Blog blog =blogRepository.findBlogById(id);
        if (blog ==null){
            throw new ApiException("blog not found");
        }else if(blog.getUser().getId()!= user_id){
            throw new ApiException("You do not have the authority to delete this blog");
        }

        blogRepository.delete(blog);
    }
    public Blog getBlogById(Integer user_id, Integer blog_id) {
        User user = userRepository.findUserById(user_id);
        if (user==null){
            throw new ApiException("user not found");
        }
        Blog blog = blogRepository.findBlogById(blog_id);
        if (blog ==null){
            throw new ApiException("blog not found");
        }else if (blog.getUser().getId()!=user_id) {
            throw new ApiException("you dont have permission");
        }

        return blog;
    }

public Blog getBlogByTitle(String title) {


    Blog blog = blogRepository.findBlogByTitle(title);
    if (blog ==null){
        throw new ApiException("blog not found");
    }

    return blog;

}
}
