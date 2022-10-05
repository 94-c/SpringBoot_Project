package com.spring.blog.data.dto;

import com.spring.blog.data.entity.Comment;
import com.spring.blog.data.entity.Post;
import com.spring.blog.data.entity.User;
import com.spring.blog.util.UserIpUtil;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {

    private Integer id;
    private Integer userId;
    private Integer postId;
    private String body;
    private String userIp;
    private Integer parentsId;

    public CommentDto(Integer userId, Integer postId, String body, String userIp) {
        this.userId = userId;
        this.postId = postId;
        this.body = body;
        this.userIp = userIp;
    }

    @Builder
    public Comment toCreateEntity() {
        User user = new User();
        user.setId(userId);

        Post post = new Post();
        post.setId(postId);

        Comment comment = new Comment();

        comment.setUser(user);
        comment.setPost(post);
        comment.setBody(body);
        comment.setUserIp(UserIpUtil.userIp(userIp));
        comment.setParentsId(parentsId);

        return comment;
    }

    @Builder
    public Comment toEditEntity(Comment comment) {
        User user = new User();
        user.setId(userId);

        Post post = new Post();
        post.setId(postId);

        comment.setId(id);
        comment.setUser(user);
        comment.setPost(post);
        comment.setBody(body);
        comment.setUserIp(UserIpUtil.userIp(userIp));
        comment.setParentsId(parentsId);

        return comment;
    }

    /* Entity -> Dto로 담아준다 */
    public static CommentDto toCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setUserId(comment.getUser().getId());
        commentDto.setPostId(comment.getPost().getId());
        commentDto.setBody(comment.getBody());
        commentDto.setUserIp(comment.getUserIp());

        return commentDto;
    }

}
