package com.spring.blog.service;

import com.spring.blog.data.dto.CommentDto;
import com.spring.blog.data.entity.Comment;
import com.spring.blog.data.entity.Post;
import com.spring.blog.util.UserIpUtil;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    public CommentDto newComment(int i) {
        CommentDto newComment = new CommentDto(6, 13, "테스트용 댓글"+i, "123123213");
        return newComment;
    }

    @Test
    @DisplayName("댓글 작성 테스트")
    public void createComment() {
        Comment newComment = commentService.createComment(newComment(10));
        assertTrue(newComment != null && newComment.getId() > 0);
    }

    @Test
    @DisplayName("댓글 찾기 테스트")
    public void findByComment() {
        Integer commentId = 3;
        CommentDto findByComment = commentService.findByComment(commentId);

        assertEquals(commentId, findByComment.getId());
    }

    @Test(expected = NullPointerException.class)
    @DisplayName("댓글 찾기 테스트")
    public void noFindByComment() {
        Integer commentId = 1;
        CommentDto findByComment = commentService.findByComment(commentId);

        assertEquals(commentId, findByComment.getId());
    }

    @Test
    @DisplayName("댓글 수정 성공")
    public void editComment() {
        CommentDto dto = new CommentDto();
        dto.setId(2);

        CommentDto findByComment = commentService.findByComment(dto.getId());
        findByComment.setBody("댓글 수정 테스트");

        Comment editComment = commentService.editComment(findByComment);

        assertTrue(editComment.getId() > 0);
        assertEquals(editComment.getClass(), Comment.class);

    }

    @Test(expected = NullPointerException.class)
    @DisplayName("댓글 수정 실패")
    public void noEditComment() {
        CommentDto dto = new CommentDto();
        dto.setId(999);

        CommentDto findByComment = commentService.findByComment(dto.getId());
        findByComment.setBody("댓글 수정 테스트");

        Comment editComment = commentService.editComment(findByComment);

        assertTrue(editComment.getId() > 0);
        assertEquals(editComment.getClass(), Comment.class);

    }

    @Test
    @DisplayName("댓글 삭제 테스트")
    public void deleteComment() {
        CommentDto dto = new CommentDto();
        dto.setId(2);

        CommentDto findByComment = commentService.findByComment(dto.getId());

        commentService.deleteComment(findByComment.getId());
    }
}
