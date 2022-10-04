package com.spring.blog.data.dto;

import com.spring.blog.data.entity.Post;
import com.spring.blog.data.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Integer id;
    private Integer userId;
    private String title;
    private String body;

    @Builder
    public Post toCreateEntity() {
        User user = new User();
        user.setId(userId);

        Post post = new Post();
        post.setUser(user);
        post.setTitle(title);
        post.setBody(body);

        return post;
    }

    @Builder
    public Post toEditEntity() {
        User user = new User();
        user.setId(userId);

        Post post = new Post();
        post.setId(id);
        post.setUser(user);
        post.setTitle(title);
        post.setBody(body);

        return post;
    }



}
