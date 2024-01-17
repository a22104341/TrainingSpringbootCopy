package pt.ulusofona.tfc.forum.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pt.ulusofona.tfc.forum.thymeleaf.entity.Post;
import pt.ulusofona.tfc.forum.thymeleaf.mapper.Mapper;
import pt.ulusofona.tfc.forum.thymeleaf.service.PostService;


@Controller
@RequestMapping("/topics/posts")
public class PostController {

	
    private final PostService postService;
    
    private final Mapper mapper;

    
    public PostController(PostService postService, Mapper mapper) {
        this.postService = postService;
        this.mapper = mapper;
    }

    @GetMapping("/edit-form/{id}")
    public String editForm(@PathVariable(value = "id") Long id, Model model) {
    	
    	
    	/*
    	 * Service Invocation
    	 */
    	
    	Post post = postService.getPost(id);
    	
    	
    	/*
    	 * Mapper & Result
    	 */
    	
    	model.addAttribute("post", mapper.postEntityToModel(post));

    	
    	return "edit-post";
    	
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(value = "id") Long id, String text, Model model) {
    	
    	
    	/*
    	 * Service invocation
    	 */
    	
    	Post post = postService.edit(id, text);
    	
    	
    	return "redirect:/topics/" + post.getTopic().getId();
    	
    }
    
}
