package by.javaguru.controller;

import by.javaguru.dto.UserCreateEditDto;
import by.javaguru.dto.UserReadDto;
import by.javaguru.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String allUsersPage(Model model) {
        List<UserReadDto> users = userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/{id}")
    public String updateUserPage(@PathVariable Long id,
                                 Model model) {
        Optional<UserReadDto> userById = userService.findById(id);
        model.addAttribute("user", userById.get());
        return "updateUser";
    }

    @PutMapping
    public String updateUser(@ModelAttribute UserCreateEditDto dto) {
        userService.save(dto);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
