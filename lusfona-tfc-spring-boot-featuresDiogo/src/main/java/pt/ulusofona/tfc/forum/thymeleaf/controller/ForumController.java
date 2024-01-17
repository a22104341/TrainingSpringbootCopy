package pt.ulusofona.tfc.forum.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForumController {

    @GetMapping("/")
    public String home() {
        return "redirect:/topics/all";
    }    
    
}
