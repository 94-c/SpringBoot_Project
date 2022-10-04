package com.spring.blog.service;

import com.spring.blog.data.dto.UserDto;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;


    public UserDto newUser() {
        return new UserDto("123", "123", "123");
    }

    @Test
    @Transactional
    @Rollback(value = true)
    @DisplayName("회원가입 테스트")
    public void userJoinTest() {
        Integer joinId = userService.join(newUser());
        UserDto userDto = userService.findById(joinId);
        assertThat(newUser().getEmail()).isEqualTo(userDto.getEmail());
    }

    @Test
    @Transactional
    @Rollback(value = true)
    @DisplayName("로그인")
    public void userLoginTest() {
        final String email = "123";
        final String password = "123";
        final String name = "123";
        UserDto loginResult = new UserDto(email, password, name);

        UserDto result = userService.login(loginResult);

        assertThat(result).isNotNull();
    }



    @Test
    @Transactional
    @Rollback(value = true)
    @DisplayName("회원 가입 후 로그인 테스트")
    public void userJoinAfterLoginTest() {
        final String email = "aaa@aaa.com";
        final String password = "123123";
        final String name = "테스트";
        UserDto userDto = new UserDto(email, password, name);

        Integer join = userService.join(userDto);

        UserDto loginUserDto = new UserDto();
        loginUserDto.setEmail(email);
        loginUserDto.setPassword(password);
        loginUserDto.setName(name);

        UserDto loginResult = userService.login(loginUserDto);

        assertThat(loginResult).isNotNull();

    }
}