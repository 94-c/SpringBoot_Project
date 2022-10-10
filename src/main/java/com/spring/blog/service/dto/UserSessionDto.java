package com.spring.blog.service.dto;

import com.spring.blog.data.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class UserSessionDto implements Serializable {

    private Integer id;
    private String email;
    private String name;

    @Builder
    public UserSessionDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
    }
}
