package com.spring.blog.repository;

import com.spring.blog.data.entity.Comment;
import com.spring.blog.data.entity.Post;
import com.spring.blog.data.entity.User;
import com.spring.blog.data.repository.CommentRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    @DisplayName("댓글 생성")
    public void createComment() {
        User user = new User();
        user.setId(6);

        Post post = new Post();
        post.setId(13);

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setPost(post);
        comment.setBody("졸립다.");
        comment.setUserIp("1231123");
        comment.setDeleteState(0);
        comment.setCreatedAt(new Date());

        Comment newComment = commentRepository.save(comment);

        assertEquals(comment, newComment);
    }

    @Test
    @DisplayName("댓글 찾기")
    public void findByComment() {
        Integer commentId = 2;

        Optional<Comment> findById = commentRepository.findById(commentId);
        //ifPresent()는 객체의 값을 가지고 있으면 실행, 없으면 넘어간다.
        findById.ifPresent(comment -> {
            System.out.println("comment : " + comment.getBody());
        });
    }

    @Test
    @DisplayName("댓글 수정")
    public void editComment() {
        Integer commentId = 2;
        Optional<Comment> findByComment = commentRepository.findById(commentId);

        findByComment.ifPresent(comment -> {
            comment.setBody("테스트 중입니다.");
            comment.setUpdatedAt(new Date());
            commentRepository.save(comment);
            System.out.println("comment : " + comment.getBody());
            System.out.println("comment : " + comment.getUpdatedAt());
        });
    }


    @Test
    @DisplayName("댓글 수정")
    public void notEditComment() {
        Integer commentId = 999;
        Optional<Comment> findByComment = commentRepository.findById(commentId);

        findByComment.ifPresent(comment -> {
            comment.setBody("테스트 중입니다.");
            comment.setUpdatedAt(new Date());
            commentRepository.save(comment);
            System.out.println("comment : " + comment.getBody());
            System.out.println("comment : " + comment.getUpdatedAt());
        });
    }

    @Test
    @DisplayName("댓글 삭제")
    public void deleteComment() {
        Integer commentId = 2;
        Optional<Comment> findByComment = commentRepository.findById(commentId);

        findByComment.ifPresent(comment -> {
            commentRepository.deleteById(comment.getId());
        });

    }
}
