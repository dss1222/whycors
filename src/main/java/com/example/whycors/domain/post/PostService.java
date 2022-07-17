package com.example.whycors.domain.post;


import com.example.whycors.domain.post.dto.PostRequestDto;
import com.example.whycors.domain.post.dto.PostResponseDto;
import com.example.whycors.domain.post.dto.UpdatePostRequestDto;
import com.example.whycors.domain.post.repository.CustomPostRepository;
import com.example.whycors.domain.post.repository.PostRepository;
import com.example.whycors.global.exceptionhandler.ErrorMessage;
import com.example.whycors.global.exceptionhandler.ex.PostException;
import com.example.whycors.global.exceptionhandler.ex.UserException;
import com.example.whycors.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CustomPostRepository customPostRepository;


    @Transactional
    public Long createPost(UserDetailsImpl userDetails, PostRequestDto requestDto) {
        Post post = new Post(requestDto.getTitle(), requestDto.getContent(), userDetails.getUser(), requestDto.getLock());
        postRepository.save(post);

        return post.getId();
    }
    @Transactional
    public List<PostResponseDto> viewPostQuery(UserDetailsImpl userDetails) {
        Long id = userDetails.getUser().getId();
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();
        List<Post> postList = customPostRepository.postView(id);
        for (Post post : postList) {
            postResponseDtoList.add(new PostResponseDto(post));
        }
        return postResponseDtoList;
    }
    @Transactional
    public List<PostResponseDto> viewPostService(UserDetailsImpl userDetails) {
        Long id = userDetails.getUser().getId();
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();
        List<Post> postList = postRepository.findAll();

        for (Post post : postList) {
            if (!post.getLock()) {
                postResponseDtoList.add(new PostResponseDto(post));
            } else {
                if (post.getMember().getId().equals(id)) {
                    postResponseDtoList.add(new PostResponseDto(post));
                }
            }
        }
        return postResponseDtoList;
    }
    @Transactional
    public Map<String, Object> editPost(Long postId, UpdatePostRequestDto requestDto, UserDetailsImpl userDetails) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new PostException(ErrorMessage.notPosts));

        Map<String, Object> result = new HashMap<>();

        if (post.getMember().getId().equals(userDetails.getUser().getId())) {
            post.updatePost(requestDto.getContent());
            result.put("result", true);
        } else {
            throw new UserException(ErrorMessage.notCreator);
        }
        return result;
    }
    @Transactional
    public Map<String, Object> deletePost(Long postId, UserDetailsImpl userDetails) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new PostException(ErrorMessage.notPosts));

        Map<String, Object> result = new HashMap<>();

        if (post.getMember().getId().equals(userDetails.getUser().getId())) {
            postRepository.deleteById(userDetails.getUser().getId());
            result.put("result", true);
        } else {
            throw new UserException(ErrorMessage.notCreator);
        }
        return result;
    }

}
