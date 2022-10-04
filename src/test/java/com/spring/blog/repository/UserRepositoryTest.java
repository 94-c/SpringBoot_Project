package com.spring.blog.repository;

import com.spring.blog.data.dto.UserDto;
import com.spring.blog.data.entity.Post;
import com.spring.blog.data.entity.User;
import com.spring.blog.data.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findAllUserList() {
        List<User> userList = userRepository.findAll();

        userList.forEach(u -> System.out.println(u.getEmail() + " , "));

        assertTrue(userList.size() > 0);
    }

    @Test
    public void joinUserTest() {
        User user = new User();
        user.setEmail("테스트");
        user.setPassword("테스트 진행 중");
        user.setCreatedAt(new Date());

        userRepository.save(user);

        assertThat(user.getId()).isGreaterThan(0);
    }

    @Test
    public void loginTest() {
        UserDto dto = new UserDto();
        dto.setEmail("테스트");

        Optional<User> optionalUser = userRepository.findByEmail(dto.getEmail());

        assertTrue(optionalUser.isPresent());
    }

    @Test
    public void loginFailTest() {
        UserDto dto = new UserDto();
        dto.setEmail("999999999999");

        Optional<User> optionalUser = userRepository.findByEmail(dto.getEmail());

        assertNotNull(optionalUser.isPresent());
    }







}
