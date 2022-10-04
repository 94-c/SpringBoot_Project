package com.spring.blog.data.dto;

import com.spring.blog.data.entity.User;
import com.spring.blog.util.Md5Util;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Integer id;
    private String email;
    private String password;
    private String name;

    public UserDto(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    @Builder
    public User toCreateEntity() {
        User user = new User();
        user.setEmail(email);
        user.setPassword(Md5Util.md5(password));
        user.setName(name);
        user.setCreatedAt(new Date());
        return user;
    }

    @Builder
    public User toEditEntity(User user) {
        user.setId(id);
        user.setEmail(email);
        user.setPassword(Md5Util.md5(password));
        user.setName(name);
        user.setUpdatedAt(new Date());
        return user;
    }

    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setName(user.getName());
        return userDto;
    }
}
