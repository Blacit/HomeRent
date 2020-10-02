package task.homerent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import task.homerent.dto.UserDto;
import task.homerent.model.Log;
import task.homerent.repository.LogRepository;
import task.homerent.repository.UserRepository;
import task.homerent.service.LogService;
import task.homerent.service.UserService;

@RestController
@RequestMapping
public class RegistrationController {


    private PasswordEncoder passwordEncoder;

    private UserService userService;

    private LogService logService;

    @Autowired
    public RegistrationController(PasswordEncoder passwordEncoder, UserService userService, LogService logService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.logService = logService;
    }

    @ResponseBody
    @PostMapping("/user/registration")
    public UserDto registerNewUserAccount(@RequestBody UserDto accountDto) {
        userService.addUser(accountDto);

        Log log = new Log();
        log.setWho(accountDto.getEmail());
        log.setEvent("registered");
        logService.save(log);
        return accountDto;
    }
}