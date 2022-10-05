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

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;


    public UserDto newUser(int i) {
        UserDto newUser = new UserDto("테스트용 계정"+i, "테스트용 패스워드"+i, "테스트용 이름"+i);
        return newUser;
    }

    @Test
    @Transactional
    @Rollback(value = true)
    @DisplayName("회원가입 테스트")
    public void userJoinTest() {
        Integer joinId = userService.join(newUser(1));
        UserDto userDto = userService.findById(joinId);
        assertThat(newUser(1).getEmail()).isEqualTo(userDto.getEmail());
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

    @Test
    @DisplayName("회원 데이터 저장")
    public void userSave() {
        IntStream.rangeClosed(1, 20).forEach(i -> {
            userService.join(newUser(i));
        });
    }

    @Test
    @DisplayName("회원 정보 삭제")
    public void userDeleteTest() {
        /**
         * 신규 회원 등록 후 → 삭제 → 삭제 후 회원 정보 null 처리
         */
        Integer saveId = userService.join(newUser(999));
        userService.delete(saveId);
        assertThat(userService.findById(saveId)).isNull();
    }
}
