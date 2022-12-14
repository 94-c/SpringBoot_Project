package com.spring.blog.controller;

import com.spring.blog.data.dto.UserDto;
import com.spring.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final Logger logger = LogManager.getLogger(UserController.class);

    @GetMapping("/join")
    public String joinForm() {
        return "user/join";
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
        if (loginResult == null) {
            logger.warn("logError");
            return "user/login";
        }
        session.setAttribute("sessionId", loginResult.getId());
        session.setAttribute("sessionEmail", loginResult.getEmail());
        session.setAttribute("sessionName", loginResult.getName());
        return "redirect:/";

    }

    @GetMapping("/")
    public String findAllUserList(Model model) {
        List<UserDto> userDtoList = userService.findAllUserList();
        model.addAttribute("userList", userDtoList);
        return "user/list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        UserDto userDto = userService.findByUser(id);
        model.addAttribute("user", userDto);
        return "user/detail";
    }

    // ajax ????????????
    @PostMapping("/ajax/{id}")
    public @ResponseBody
    UserDto findByIdAjax(@PathVariable Integer id) {
        UserDto userDto = userService.findByUser(id);
        return userDto;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        userService.delete(id);
        return "redirect:/user/";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAjax(@PathVariable Integer id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK); // ajax ????????? ????????? ???????????? 200 ????????? ???
    }


    @GetMapping("/edit")
    public String editForm(HttpSession session, Model model) {
        Integer id = (Integer) session.getAttribute("sessionId");
        UserDto dto = userService.findByUser(id);
        model.addAttribute("user", dto);
        return "user/edit";
    }

    @PostMapping("/editProc")
    public String editProc(@ModelAttribute UserDto dto) {
        userService.editUser(dto);
        return "redirect:/user/";
    }

    //????????? ????????????
    @PostMapping("/emailCheck")
    public @ResponseBody
    String emailCheck(@RequestParam String email) {
        String result = userService.emailCheck(email);
        return result;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }
}
