package com.example.whycors.domain.post;

import com.example.whycors.domain.post.dto.PostRequestDto;
import com.example.whycors.domain.post.dto.PostResponseDto;
import com.example.whycors.domain.post.dto.UpdatePostRequestDto;
import com.example.whycors.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public Map<String, Object> createPost(@RequestBody PostRequestDto requestDto,
                                          @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        Long postId = postService.createPost(userDetails, requestDto);
        HashMap<String, Object> result = new HashMap<>();
        result.put("postId", postId);
        return result;
    }

    @GetMapping("posts2")
    public List<PostResponseDto> viewPostQuery(@AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        return postService.viewPostQuery(userDetails);
    }

    @GetMapping("posts")
    public List<PostResponseDto> viewPostService(@AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        return postService.viewPostService(userDetails);
    }

    @PatchMapping("posts/{postId}")
    public Map<String, Object> updatePost(@PathVariable Long postId,
                                          @RequestBody UpdatePostRequestDto requestDto,
                                          @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.editPost(postId, requestDto, userDetails);
    }

    @DeleteMapping("posts/{postId}")
    public Map<String, Object> deletePost(@PathVariable Long postId,
                                          @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.deletePost(postId, userDetails);
    }
}
