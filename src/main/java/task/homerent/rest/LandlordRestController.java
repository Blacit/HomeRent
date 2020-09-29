package task.homerent.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import task.homerent.model.House;
import task.homerent.model.Role;
import task.homerent.model.Status;
import task.homerent.model.User;
import task.homerent.repository.HouseRepository;
import task.homerent.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/landlord")
public class LandlordRestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HouseRepository houseRepository;

    public LandlordRestController(UserRepository userRepository, HouseRepository houseRepository) {
        this.userRepository = userRepository;
        this.houseRepository = houseRepository;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('landlord:write')")
    public String landlordUpdate(@PathVariable(value = "id") Long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setRole(Role.TENANT);
        Iterable<House> house = houseRepository.findById_landlord(id);
        for (House a : house) {
            a.setStatus(Status.INACTIVELY);
            userRepository.save(user);
        }
        return "присвоена роль TENANT и деактивированы квартиры, если были подключены\n" + user;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public List<User> landlordGet() {
        return userRepository.findByRole(Role.LANDLORD);
    }
}
