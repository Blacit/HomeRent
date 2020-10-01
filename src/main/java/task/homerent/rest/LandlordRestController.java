package task.homerent.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import task.homerent.model.*;
import task.homerent.repository.HouseRepository;
import task.homerent.repository.LogRepository;
import task.homerent.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/landlord")
public class LandlordRestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private LogRepository logRepository;

    public LandlordRestController(UserRepository userRepository, HouseRepository houseRepository, LogRepository logRepository) {
        this.userRepository = userRepository;
        this.houseRepository = houseRepository;
        this.logRepository = logRepository;
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

        Log log = new Log();
        log.setWho(String.valueOf(userRepository.findById(id).orElseThrow()));
        log.setEvent("update landlord");
        logRepository.save(log);
        return "присвоена роль TENANT и деактивированы квартиры, если были подключены\n" + user;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public List<User> landlordGet() {
        return userRepository.findByRole(Role.LANDLORD);
    }
}
