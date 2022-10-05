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
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @GetMapping("/create")
    public String createPost() {
        return "post/input";
    }

    @PostMapping("/createProc")
    public String createProc(@ModelAttribute PostDto dto) {
        postService.createPost(dto);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        PostDto result = postService.findByPost(id);
        model.addAttribute("post", result);
        return "post/detail";
    }

    /*@GetMapping("/edit")
    public String editForm(HttpSession session, Model model) {
        Integer id = (Integer) session.getAttribute("sessionId");
        PostDto dto = postService.findById(id);
        model.addAttribute("post", dto);
        return "post/detail";
    }*/

    @PostMapping("/editProc")
    public String editProc(@ModelAttribute PostDto dto) {
        postService.editPost(dto);
        return "redirect:/";
    }



}
