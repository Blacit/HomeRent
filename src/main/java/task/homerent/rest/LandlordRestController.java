package task.homerent.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import task.homerent.model.Role;
import task.homerent.model.User;
import task.homerent.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/landlord")
public class LandlordRestController {

    @Autowired
    private UserRepository userRepository;

    public LandlordRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // TENANT становится LANDLORD
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public void landlordPostAdd(@PathVariable(value = "id") Long id) {
        User user = userRepository.findById(id).orElseThrow();
        Role role = Role.LANDLORD;
        user.setRole(role);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public User landlordGet(@PathVariable(value = "id") Long id) {
        List<User> user = userRepository.findByRole("TENANT");
        user.
        return userRepository.findById(id).getL
    }
}
