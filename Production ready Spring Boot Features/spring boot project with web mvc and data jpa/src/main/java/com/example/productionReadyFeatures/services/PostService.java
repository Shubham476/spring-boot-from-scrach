package com.example.productionReadyFeatures.services;

import com.example.productionReadyFeatures.dto.PostDTO;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PostService {

    List<PostDTO> getAllPost();
    PostDTO createNewPost(PostDTO inputPost);
    PostDTO getPostById(Long postId);
}
