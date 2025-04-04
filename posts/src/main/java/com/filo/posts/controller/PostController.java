package com.filo.posts.controller;

import com.filo.posts.dto.PostDTO;
import com.filo.posts.model.PostModel;
import com.filo.posts.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostModel> createPost(@RequestBody @Valid PostDTO postDTO){
        return ResponseEntity.ok(this.postService.createPost(postDTO));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostModel> updatePost(@RequestBody @Valid PostDTO postDTO, @PathVariable String id) {
        return ResponseEntity.ok(this.postService.updatePost(postDTO, id));

    }

}
