package task.homerent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import task.homerent.dto.UserDto;
import task.homerent.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public String getLoginPage(@RequestBody UserDto userDto) {
        userService.loginUser(userDto);
        return "login";
    }
}
