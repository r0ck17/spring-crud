package by.javaguru.controller;

import by.javaguru.dto.UserReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
public class ViewController {

    @GetMapping("/user")
    public String userPage(@SessionAttribute("user") UserReadDto user, Model model) {
        model.addAttribute("user", user);
        return "user";
    }
}
