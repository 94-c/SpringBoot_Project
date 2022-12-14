package com.spring.blog.data.dto;

import com.spring.blog.data.entity.Post;
import com.spring.blog.data.entity.User;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class PostDto {

    //TODO Dto와 Mapper 분리, 매퍼스트럭처 사용해서 클래스하고 비교
    private Integer id;
    private Integer userId;
    private String title;
    private String body;
    private List<CommentDto> commentList;

    //테스트 코드 작성
    public PostDto(Integer userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public Post toCreateEntity() {
        User user = new User();
        user.setId(userId);

        Post post = new Post();
        post.setUser(user);
        post.setTitle(title);
        post.setBody(body);

        return post;
    }

    public Post toEditEntity(Post post) {
        User user = new User();
        user.setId(userId);

        post.setId(id);
        post.setUser(user);
        post.setTitle(title);
        post.setBody(body);

        return post;
    }

    /* Entity -> Dto로 담아준다 */
    public static PostDto toPostDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setUserId(post.getUser().getId());
        postDto.setTitle(post.getTitle());
        postDto.setBody(post.getBody());

        return postDto;
    }



}
