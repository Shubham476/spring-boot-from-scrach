package com.example.productionReadyFeatures.services;

import com.example.productionReadyFeatures.dto.PostDTO;
import com.example.productionReadyFeatures.entities.PostEntity;
import com.example.productionReadyFeatures.exception.ResourceNotFoundException;
import com.example.productionReadyFeatures.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    public final PostRepository postRepository;
    public final ModelMapper modelMapper;

    @Override
    public List<PostDTO> getAllPost() {
        return postRepository
                .findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity, PostDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO createNewPost(PostDTO inputPost) {
        PostEntity postEntity = modelMapper.map(inputPost, PostEntity.class);
        PostEntity savedEntity = postRepository.save(postEntity);
        return modelMapper.map(savedEntity, PostDTO.class);

    }

    @Override
    public PostDTO getPostById(Long postId) {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post Not found with Id"+postId));
        return modelMapper.map(postEntity, PostDTO.class);
    }
}
