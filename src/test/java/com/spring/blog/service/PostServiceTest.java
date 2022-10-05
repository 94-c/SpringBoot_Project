package com.spring.blog.service;

import com.spring.blog.data.dto.PostDto;
import com.spring.blog.data.entity.Post;
import com.spring.blog.data.entity.User;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostService postService;

    public PostDto newPost() {
        return new PostDto(6, "테스트", "테스트 중");
    }

    @Test
    @DisplayName("글작성 테스트")
    public void createPost() {
        Post newPost = postService.createPost(newPost());
        assertTrue(newPost != null && newPost.getId() > 0);
    }

    @Test
    @DisplayName("글 찾기 테스트")
    public void findByPostId() {
        Integer postId = 13;
        PostDto findById = postService.findById(postId);

        assertNotNull(findById);
    }

    @Test(expected = AssertionError.class)
    @DisplayName("글 찾기 테스트")
    public void noFindByPostId() {
        Integer postId = 999;
        PostDto findById = postService.findById(postId);

        assertNotNull(findById);
    }



}
