package task.homerent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.*;
import task.homerent.dto.UserDto;
import task.homerent.model.Log;
import task.homerent.repository.LogRepository;
import task.homerent.service.LogService;
import task.homerent.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    private final LogService logService;

    @Autowired
    public AuthController(UserService userService, LogService logService) {
        this.userService = userService;
        this.logService = logService;
    }

    @PostMapping("/login")
    public String getLoginPage(@RequestBody UserDto userDto) {
        userService.loginUser(userDto);

        Log log = new Log();
        log.setWho(userDto.getEmail());
        log.setEvent("log in");
        logService.save(log);
        return "login";
    }
}
