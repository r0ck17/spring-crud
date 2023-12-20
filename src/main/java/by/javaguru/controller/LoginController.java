package by.javaguru.controller;

import by.javaguru.dto.UserReadDto;
import by.javaguru.exception.UserNotFoundException;
import by.javaguru.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequiredArgsConstructor
@SessionAttributes("user")
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    @GetMapping
    public String loginPage() {
        return "login";
    }

    @PostMapping
    public String userLogin(@RequestParam String username,
                            @RequestParam String password,
                            Model model) {
        UserReadDto user;
        try {
            user = userService.findUserByUsernameAndPassword(username, password);
        } catch (UserNotFoundException e) {
            model.addAttribute("loginError", "Wrong username or password");
            return "login";
        }

        model.addAttribute("user", user);
        return switch (user.getRole()) {
            case USER -> "redirect:/user";
            case ADMIN -> "redirect:/users";
        };
    }
}
