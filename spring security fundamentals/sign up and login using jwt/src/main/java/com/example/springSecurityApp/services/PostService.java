package com.example.springSecurityApp.services;



import com.example.springSecurityApp.dto.PostDTO;

import java.util.List;


public interface PostService {

    List<PostDTO> getAllPost();
    PostDTO createNewPost(PostDTO inputPost);
    PostDTO getPostById(Long postId);

    PostDTO updatePost(PostDTO inputPost, Long postId);
}
