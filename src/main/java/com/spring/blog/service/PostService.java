package com.spring.blog.service;

import com.spring.blog.data.dto.PostDto;
import com.spring.blog.data.entity.Post;
import com.spring.blog.data.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<PostDto> findAllPostList() {
        List<Post> postList = postRepository.findAll();
        List<PostDto> postDtoList = new ArrayList<>();
        for (Post post : postList) {
            postDtoList.add(PostDto.toPostDto(post));
        }
        return postDtoList;
    }

    public Post createPost(PostDto dto) {
        Post newPost = dto.toCreateEntity();
        return postRepository.save(newPost);
    }

    public PostDto findById(Integer id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post =optionalPost.get();
            return PostDto.toPostDto(post);
        }
        return null;
    }

    public void editPost(PostDto dto) {
        Optional<Post> findById = postRepository.findById(dto.getId());
        Post editPost = dto.toEditEntity(findById.get());
        postRepository.save(editPost).getId();
    }
}
