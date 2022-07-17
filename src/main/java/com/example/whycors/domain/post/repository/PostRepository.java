package com.example.whycors.domain.post.repository;

import com.example.whycors.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
