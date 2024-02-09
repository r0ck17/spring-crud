package by.javaguru.controller;

import by.javaguru.dto.UserCreateEditDto;
import by.javaguru.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    public String loginPage(Model model) {
        model.addAttribute("userCreateEditDto", new UserCreateEditDto());
        return "registration";
    }

    @PostMapping
    public String userLogin(@ModelAttribute @Validated UserCreateEditDto dto,
                            BindingResult bindingResult,
                            Model model) {
//        if (!model.containsAttribute("userCreateEditDto")) {
//            model.addAttribute("user", new UserCreateEditDto());
//        }
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(dto);
        model.addAttribute("userRegistered", "Successful registration");

        return "login";
    }
}
