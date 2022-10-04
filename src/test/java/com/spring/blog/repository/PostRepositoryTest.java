package com.spring.blog.repository;

import com.spring.blog.data.entity.Post;
import com.spring.blog.data.repository.PostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    public void createPostTest() {
        Post post = new Post();
        post.setTitle("테스트");
        post.setBody("테스트 진행 중");
        post.setCreatedAt(new Date());

        postRepository.save(post);

        assertThat(post.getId()).isGreaterThan(0);
    }

    @Test
    public void findPostIdTest() {

        Optional<Post> post = postRepository.findById(3);

        assertTrue(post.isPresent());

        System.out.println(post.toString());
    }

    @Test
    public void getListOfPostTest() {

        List<Post> postList = postRepository.findAll();

        assertThat(postList.size()).isGreaterThan(0);

    }

    @Test
    public void updatePostTest() {

        Post post = postRepository.findById(3).get();

        post.setTitle("테스트 진행 중");
        post.setBody("테스트 바디 진행 중");
        post.setUpdatedAt(new Date());

        Post editPost = postRepository.save(post);

        assertThat(editPost.getTitle()).isEqualTo("테스트 진행 중");
        assertThat(editPost.getBody()).isEqualTo("테스트 바디 진행 중");

    }
}
