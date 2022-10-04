package com.spring.blog.controller;

import com.spring.blog.data.dto.PostDto;
import com.spring.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final PostService postService;

    @GetMapping("/")
    public String index(Model model) {
        List<PostDto> postDtoList = postService.findAllPostList();
        model.addAttribute("postList", postDtoList);
        return "index";
    }
}
