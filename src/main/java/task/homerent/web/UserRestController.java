package task.homerent.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import task.homerent.model.Status;
import task.homerent.model.User;
import task.homerent.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {

    private final UserRepository userRepository;

    @Autowired
    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public List<User> userActiveGet() {
        return userRepository.findByStatus(Status.ACTIVE);
    }
}