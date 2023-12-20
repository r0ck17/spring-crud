package by.javaguru.controller;

import by.javaguru.dto.UserCreateEditDto;
import by.javaguru.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;

    @GetMapping
    public String loginPage() {
        return "registration";
    }

    @PostMapping
    public String userLogin(@ModelAttribute UserCreateEditDto dto,
                            Model model) {
        userService.save(dto);
        model.addAttribute("userRegistered", "Successful registration");
        return "login";
    }
}
