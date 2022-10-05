package com.spring.blog.service;

import com.spring.blog.data.dto.UserDto;
import com.spring.blog.data.entity.User;
import com.spring.blog.data.repository.UserRepository;
import com.spring.blog.util.Md5Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserDto> findAllUserList() {
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            userDtoList.add(UserDto.toUserDto(user));
        }
        return userDtoList;
    }

    public Integer join(UserDto dto) {
        User newUser = dto.toCreateEntity();
        return userRepository.save(newUser).getId();
    }

    public UserDto login(UserDto dto) {
        Optional<User> optionalUser = userRepository.findByEmail(dto.getEmail());
        if (optionalUser.isPresent()) {
            User loginUser = optionalUser.get();
            if (loginUser.getPassword().equals(Md5Util.md5(dto.getPassword()))) {
                return UserDto.toUserDto(loginUser);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public UserDto findById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return UserDto.toUserDto(user);
        } else {
            return null;
        }
    }

    public void editUser(UserDto dto) {
        Optional<User> findById = userRepository.findById(dto.getId());
        User editUser = dto.toEditEntity(findById.get());
        userRepository.save(editUser).getId();
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    public String emailCheck(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            return "ok";
        } else {
            return "no";
        }
    }
}
