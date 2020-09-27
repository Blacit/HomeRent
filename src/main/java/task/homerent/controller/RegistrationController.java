package task.homerent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import task.homerent.dto.UserDto;
import task.homerent.model.Role;
import task.homerent.model.Status;
import task.homerent.model.User;
import task.homerent.repository.UserRepository;

@Controller
@RequestMapping
public class RegistrationController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    public RegistrationController(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @PostMapping("/user/registration")
    public User registerNewUserAccount(@RequestBody UserDto accountDto) {
        User user = new User();
        user.setFirstName(accountDto.getFirst_name());
        user.setLastName(accountDto.getLast_name());
        // Шифруем и сохраняем
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));

        user.setEmail(accountDto.getEmail());
        Role role = Role.TENANT;
        user.setRole(role);
        Status status = Status.ACTIVE;
        user.setStatus(status);
        return userRepository.save(user);
    }
}