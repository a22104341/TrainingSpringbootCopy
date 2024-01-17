package pt.ulusofona.tfc.forum.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pt.ulusofona.tfc.forum.thymeleaf.entity.User;
import pt.ulusofona.tfc.forum.thymeleaf.mapper.Mapper;
import pt.ulusofona.tfc.forum.thymeleaf.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final Mapper mapper;

    public UserController(UserService userService, Mapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "topUsers"; // This should be the name of your Thymeleaf template
    }

    // ... (rest of the methods)
}
