package task.homerent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import task.homerent.dto.UserDto;
import task.homerent.model.Log;
import task.homerent.repository.LogRepository;
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

    @Autowired
    private LogRepository logRepository;

    public RegistrationController(PasswordEncoder passwordEncoder, UserRepository userRepository, UserService userService, LogRepository logRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userService = userService;
        this.logRepository = logRepository;
    }

    @ResponseBody
    @PostMapping("/user/registration")
    public UserDto registerNewUserAccount(@RequestBody UserDto accountDto) {
        Log log = new Log();
        log.setWho(accountDto.getEmail());
        log.setEvent("registered");
        logRepository.save(log);
        userService.addUser(accountDto);
        return accountDto;
    }
}