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
        //TODO orElse() 사용
        Optional<User> optionalUser = userRepository.findByEmail(dto.getEmail());
        if (optionalUser.isPresent()) {
            User loginUser = optionalUser.get();
            if (loginUser.getPassword().equals(Md5Util.md5(dto.getPassword()))) {
                //TODO 매퍼 클래스 생성 및 메소드 명 수정
                return UserDto.toUserDto(loginUser);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public UserDto findByUser(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return UserDto.toUserDto(user);
        } else {
            return null;
        }
    }

    public void editUser(UserDto dto) {
        //TODO orElse() 사용
        Optional<User> findById = userRepository.findById(dto.getId());
        if (findById.isPresent()) {
            User editUser = dto.toEditEntity(findById.get());
        }
        User updatedUser = userRepository.save(editUser);
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    public boolean emailCheck(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.isPresent();
    }
}
