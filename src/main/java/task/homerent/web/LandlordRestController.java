package task.homerent.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import task.homerent.model.*;
import task.homerent.repository.HouseRepository;
import task.homerent.repository.LogRepository;
import task.homerent.repository.UserRepository;
import task.homerent.service.HouseService;
import task.homerent.service.LogService;
import task.homerent.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/landlord")
public class LandlordRestController {

    private final UserService userService;
    private final HouseService houseService;
    private final LogService logService;

    @Autowired
    public LandlordRestController(UserService userService, HouseService houseService, LogService logService) {
        this.userService = userService;
        this.houseService = houseService;
        this.logService = logService;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('landlord:write')")
    public String landlordUpdate(@PathVariable(value = "id") Long id) {
        User user = userService.findById(id);
        user.setRole(Role.TENANT);
        Iterable<House> house = houseService.findById_landlord(id);
        for (House a : house) {
            a.setStatus(Status.INACTIVELY);
            userService.save(user);
        }

        Log log = new Log();
        log.setWho(String.valueOf(userService.findById(id)));
        log.setEvent("update landlord");
        logService.save(log);
        return "присвоена роль TENANT и деактивированы квартиры, если были подключены\n" + user;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public List<User> landlordGet() {
        return userService.findByRole(Role.LANDLORD);
    }
}
