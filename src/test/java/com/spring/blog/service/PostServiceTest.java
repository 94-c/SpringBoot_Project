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

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostService postService;

    public PostDto newPost() {
        return new PostDto( 1, "테스트", "테스트 중");
    }


}
