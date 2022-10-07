package com.spring.blog.service;

import com.spring.blog.data.dto.PostDto;
import com.spring.blog.data.entity.Post;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.Assert.*;

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
        PostDto findById = postService.findByPost(postId);

        assertEquals(postId, findById.getId());
    }

    @Test(expected = NullPointerException.class)
    @DisplayName("글 찾기 테스트")
    public void noFindByPostId() {
        Integer postId = 999;
        PostDto findById = postService.findByPost(postId);

        assertEquals(postId, findById.getId());
    }

    @Test
    @DisplayName("글 수정 테스트")
    public void editPost() {
        PostDto dto = new PostDto();
        dto.setId(13);

        PostDto findByPost = postService.findByPost(dto.getId());
        findByPost.setTitle("글 수정 테스트");
        findByPost.setBody("글 수정 테스트");

        Post editPost = postService.editPost(findByPost);

        assertEquals(findByPost.getId(), editPost.getId());
        assertTrue(editPost.getId() > 0);
    }

    @Test
    @DisplayName("글 수정 테스트")
    public void noEditPost() {
        PostDto dto = new PostDto();
        dto.setId(99);

        PostDto findByPost = postService.findByPost(dto.getId());
        findByPost.setTitle("글 수정 테스트");
        findByPost.setBody("글 수정 테스트");

        Post editPost = postService.editPost(findByPost);

        assertEquals(findByPost.getId(), editPost.getId());
        assertTrue(editPost.getId() > 0);
    }

    @Test
    @DisplayName("글 삭제 테스트")
    public void deletePost() {
        PostDto dto = new PostDto();
        dto.setId(1);

        PostDto findByPost = postService.findByPost(dto.getId());

        postService.deletePost(findByPost.getId());

    }


}
