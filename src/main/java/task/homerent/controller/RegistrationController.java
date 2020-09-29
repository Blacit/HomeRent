package task.homerent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import task.homerent.dto.UserDto;
import task.homerent.repository.UserRepository;
import task.homerent.service.UserService;

@RestController
@RequestMapping
public class RegistrationController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public RegistrationController(PasswordEncoder passwordEncoder, UserRepository userRepository, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @ResponseBody
    @PostMapping("/user/registration")
    public UserDto registerNewUserAccount(@RequestBody UserDto accountDto) {
        userService.addUser(accountDto);
        return accountDto;
    }
}