package com.example.springSecurityApp.controllers;


import com.example.springSecurityApp.dto.PostDTO;
import com.example.springSecurityApp.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostDTO> getAllPost(){
        return postService.getAllPost();
    }

    @GetMapping("/getPost/{postId}")
    public PostDTO getAllPost(@PathVariable Long postId){
        return postService.getPostById(postId);
    }

    @PostMapping
    public PostDTO createNewPost(@RequestBody PostDTO inputPost){
        return postService.createNewPost(inputPost);
    }

    @PutMapping("/{postId}")
    public PostDTO updatePost(@RequestBody PostDTO inputPost, @PathVariable Long postId){
        return postService.updatePost(inputPost, postId);
    }

}
