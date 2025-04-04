package com.filo.posts.repository;

import com.filo.posts.model.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostModel, String> {
}
