package com.spring.blog.controller;

import com.spring.blog.data.dto.PostDto;
import com.spring.blog.data.dto.UserDto;
import com.spring.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/create")
    public String createPost() {
        return "post/input";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute PostDto dto) {
        postService.createPost(dto);
        return "redirect:/";
    }

    @GetMapping("/read/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        PostDto result = postService.findByPost(id);
        model.addAttribute("post", result);
        return "post/detail";
    }

    @GetMapping("/edit")
    public String editPost(HttpSession session, Model model) {
        Integer id = (Integer) session.getAttribute("sessionId");
        PostDto dto = postService.findByPost(id);
        model.addAttribute("post", dto);
        return "post/detail";
    }

    @PostMapping("/edit")
    public String editPost(@ModelAttribute PostDto dto) {
        postService.editPost(dto);
        return "redirect:/";
    }

}
