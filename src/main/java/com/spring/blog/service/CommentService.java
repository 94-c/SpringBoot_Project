package com.spring.blog.service;

import com.spring.blog.data.dto.CommentDto;
import com.spring.blog.data.entity.Comment;
import com.spring.blog.data.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment createComment(CommentDto dto) {
        Comment newComment = dto.toCreateEntity();
        return commentRepository.save(newComment);
    }

    public CommentDto findByComment(Integer id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            return CommentDto.toCommentDto(comment);
        }
        return null;
    }

    public Comment editComment(CommentDto dto) {
        Optional<Comment> optionalComment = commentRepository.findById(dto.getId());
        Comment editComment = dto.toEditEntity(optionalComment.get());
        return commentRepository.save(editComment);
    }

    public void deleteComment(Integer id) {
        Optional<Comment> comment = commentRepository.findById(id);
        commentRepository.deleteById(comment.get().getId());
    }

}
