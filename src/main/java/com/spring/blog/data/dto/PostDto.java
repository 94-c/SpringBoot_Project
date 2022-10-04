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

    public PostDto(Integer userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

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
    public Post toEditEntity(Post post) {
        User user = new User();
        user.setId(userId);

        post.setId(id);
        post.setUser(user);
        post.setTitle(title);
        post.setBody(body);

        return post;
    }

    public static PostDto toPostDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setUserId(post.getUser().getId());
        postDto.setTitle(post.getTitle());
        postDto.setBody(post.getBody());

        return postDto;
    }


}
