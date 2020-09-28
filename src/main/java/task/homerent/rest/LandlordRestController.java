package task.homerent.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import task.homerent.model.Role;
import task.homerent.model.User;
import task.homerent.repository.UserRepository;

import java.util.List;

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
        user.setRole(Role.LANDLORD);
    }

    // Выводим весь список арендодателей
    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public List<User> landlordGet() {
        return userRepository.findByRole(Role.LANDLORD);
    }
}
