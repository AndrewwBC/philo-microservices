package com.filo.posts.service;

import com.filo.posts.dto.PostDTO;
import com.filo.posts.model.PostModel;
import com.filo.posts.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostModel createPost(PostDTO postDTO) {
        PostModel post = new PostModel(postDTO.content(), postDTO.userId());
        return this.postRepository.save(post);
    }

}
