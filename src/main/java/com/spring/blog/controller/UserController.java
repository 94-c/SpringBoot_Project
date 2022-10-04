package com.spring.blog.controller;

import com.spring.blog.data.dto.UserDto;
import com.spring.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/join")
    public String joinForm() {
        return "user/userInputForm";
    }

    @PostMapping("/joinProc")
    public String join(@ModelAttribute UserDto dto) {
        userService.join(dto);
        return "user/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "user/login";
    }

    @PostMapping("/loginProc")
    public String login(@ModelAttribute UserDto dto, HttpSession session) {
        UserDto loginResult = userService.login(dto);
        if (loginResult != null) {
            session.setAttribute("sessionId", loginResult.getId());
            session.setAttribute("sessionEmail", loginResult.getEmail());
            session.setAttribute("sessionName", loginResult.getName());
            return "index";
        } else {
            return "user/login";
        }
    }

    @GetMapping("/")
    public String findAllUserList(Model model) {
        List<UserDto> userDtoList = userService.findAllUserList();
        model.addAttribute("userList", userDtoList);
        return "user/list";
    }



}
