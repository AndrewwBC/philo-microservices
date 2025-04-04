package com.filo.posts.service;

import com.filo.posts.dto.PostDTO;
import com.filo.posts.model.PostModel;
import com.filo.posts.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

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

    public PostModel updatePost(PostDTO postDTO, String id) {
        PostModel post = this.postRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Post not found."));
        post.setContent(post.getContent());
        return this.postRepository.save(post);
    }

}
