package task.homerent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import task.homerent.dto.UserDto;
import task.homerent.model.Log;
import task.homerent.repository.LogRepository;
import task.homerent.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private LogRepository logRepository;

    public AuthController(UserService userService, LogRepository logRepository) {
        this.userService = userService;
        this.logRepository = logRepository;
    }

    @PostMapping("/login")
    public String getLoginPage(@RequestBody UserDto userDto) {
        Log log = new Log();
        log.setWho(userDto.getEmail());
        log.setEvent("log in");
        logRepository.save(log);

        userService.loginUser(userDto);
        return "login";
    }
}
