package com.codeup.blog.controllers;

import com.codeup.blog.models.EmailService;
import com.codeup.blog.models.Post;
import com.codeup.blog.repositories.PostRepository;
import com.codeup.blog.repositories.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostRepository postDao;
    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }


//    this is the same thing as doing the @Autowired
//    private final UserRepository userDao;
//    public PostController(UserRepository userDao) {
//        this.userDao = userDao;
//    }

    //this is how it needs to be written in order for it to work
    @Autowired
    private Users userDao;

    @Autowired
    private EmailService emailService;


    //  posts page:
    @GetMapping("/posts")
    public String all(Model model) {
//    create a new list object
//        List<Post> postList = new ArrayList<>();
////    create ad objects, hard coded
//        Post post1 = new Post("Title Test 1", "Body Test 1", 1);
//        Post post2 = new Post("Title Test 2", "Body Test 2", 2);
//        Post post3 = new Post("Title Test 3", "Body Test 3", 2);
////   this is adding the post objects to the list
//        postList.add(post1);
//        postList.add(post2);
//        postList.add(post3);

        Iterable<Post> postList = postDao.findAll();

//    send the list of post objects to the view:
        model.addAttribute("postList", postList);
        return "posts/index";
    }


    //  individual post page:
    @GetMapping("/posts/{id}")
    public String show(@PathVariable long id, Model model) {
////        //create ad objects, hard coded
////        Post postToView = new Post("Title test 1", "Body Test 1, this will show a single posts page.", id);
////        //this is adding the post objects to the list
////        String title = postToView.getTitle();
////        String body = postToView.getBody();
        Post post = postDao.findOne(id);
        model.addAttribute("post", post);
        //displays the username on the post
        String username = userDao.findOne(post.getUser_id()).getUsername();
        model.addAttribute("username", username);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String showForm() {
        return "posts/create";
    }

//  save the new post to the database
    @PostMapping("/posts/create")
      public String create(@ModelAttribute Post post) {
        Post posts = new Post();
        postDao.save(post);
//   when its a PostMapping the return will have a redirect:
        return "redirect:/posts";
    }


//  this brings up a post object in a form to edit it
    @GetMapping("/posts/{id}/edit")
    public String edit(@PathVariable long id, Model model) {
        Post post = postDao.findOne(id);
        model.addAttribute("post", post);
        return "posts/edit";
    }

//  this will save the edited post object
    @PostMapping("/posts/{id}/edit")
    public String update(@ModelAttribute Post post) {
        postDao.save(post);
 //   when its a PostMapping the return will have a redirect:
        return "redirect:/posts";
    }

//  this deletes a post
    @PostMapping("/posts/delete")
    public String delete(@RequestParam(name = "id") long id) {
        postDao.delete(id);
//   when its a PostMapping the return will have a redirect:
        return "redirect:/posts";
    }







}
