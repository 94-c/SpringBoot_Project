package com.spring.blog.controller;

import com.spring.blog.data.dto.UserDto;
import com.spring.blog.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public String login(@ModelAttribute UserDto dto, HttpSession session, HttpServletRequest request) {
        UserDto loginResult = userService.login(dto);
        if (loginResult != null) {
            session.setAttribute("sessionId", loginResult.getId());
            session.setAttribute("sessionEmail", loginResult.getEmail());
            session.setAttribute("sessionName", loginResult.getName());
            return "index";
        } else {
            request.setAttribute("message", "아이디 및 비밀번호를 확인해주세요.");
            return "user/login";
        }
    }

    @GetMapping("/")
    public String findAllUserList(Model model) {
        List<UserDto> userDtoList = userService.findAllUserList();
        model.addAttribute("userList", userDtoList);
        return "user/list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        UserDto userDto = userService.findById(id);
        model.addAttribute("user", userDto);
        return "user/detail";
    }


}
