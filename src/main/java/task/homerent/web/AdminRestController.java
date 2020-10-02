package task.homerent.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import task.homerent.model.House;
import task.homerent.model.Log;
import task.homerent.model.Status;
import task.homerent.model.User;
import task.homerent.service.HouseService;
import task.homerent.service.LogService;
import task.homerent.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    private final HouseService houseService;
    private final UserService userService;
    private final LogService logService;

    @Autowired
    public AdminRestController(HouseService houseService, UserService userService, LogService logService) {
        this.houseService = houseService;
        this.userService = userService;
        this.logService = logService;
    }


    @DeleteMapping("/house/{id}")
    @PreAuthorize("hasAuthority('admin:write')")
    public String deleteHouseById(@PathVariable Long id) {
        Log log = new Log();
        log.setWho(String.valueOf(houseService.findById(id).orElseThrow()));
        log.setEvent("apartment removed");
        logService.save(log);

        House house = houseService.findById(id).orElseThrow();
        houseService.delete(house);
        return "Квартира удалена " + house;
    }

    @DeleteMapping("user/{id}")
    @PreAuthorize("hasAuthority('admin:write')")
    public String deleteUserById(@PathVariable Long id) {
        Log log = new Log();
        log.setWho(String.valueOf(userService.findById(id).orElseThrow()));
        log.setEvent("user removed");
        logService.save(log);

        User user = userService.findById(id).orElseThrow();
        Iterable<House> house = houseService.findById_landlord(id);
        for (House a : house) {
            houseService.delete(a);
        }
        userService.delete(user);
        return "Пользователь удалён: " + user;
    }

    @PutMapping("/user/banned/{id}")
    @PreAuthorize("hasAuthority('admin:write')")
    public String bannedUserById(@PathVariable Long id) {
        Log log = new Log();
        log.setWho(String.valueOf(userService.findById(id).orElseThrow()));
        log.setEvent("user banned");
        logService.save(log);

        User user = userService.findById(id).orElseThrow();
        user.setStatus(Status.BANNED);
        Iterable<House> house = houseService.findById_landlord(id);
        for (House a : house) {
            a.setStatus(Status.INACTIVELY);
            userService.save(user);
        }
        return "Пользователь заблокирован: " + user;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public List<User> userAllGet() {
        return userService.findAll();
    }
}