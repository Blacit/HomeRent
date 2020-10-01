package task.homerent.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import task.homerent.model.House;
import task.homerent.model.Log;
import task.homerent.model.Status;
import task.homerent.model.User;
import task.homerent.repository.HouseRepository;
import task.homerent.repository.LogRepository;
import task.homerent.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LogRepository logRepository;

    public AdminRestController(HouseRepository houseRepository, UserRepository userRepository, LogRepository logRepository) {
        this.houseRepository = houseRepository;
        this.userRepository = userRepository;
        this.logRepository = logRepository;
    }

    @DeleteMapping("/house/{id}")
    @PreAuthorize("hasAuthority('admin:write')")
    public String deleteHouseById(@PathVariable Long id) {
        Log log = new Log();
        log.setWho(String.valueOf(houseRepository.findById(id).orElseThrow()));
        log.setEvent("apartment removed");
        logRepository.save(log);

        House house = houseRepository.findById(id).orElseThrow();
        houseRepository.delete(house);
        return "Квартира удалена " + house;
    }

    @DeleteMapping("user/{id}")
    @PreAuthorize("hasAuthority('admin:write')")
    public String deleteUserById(@PathVariable Long id) {
        Log log = new Log();
        log.setWho(String.valueOf(userRepository.findById(id).orElseThrow()));
        log.setEvent("user removed");
        logRepository.save(log);

        User user = userRepository.findById(id).orElseThrow();
        Iterable<House> house = houseRepository.findById_landlord(id);
        for (House a : house) {
            houseRepository.delete(a);
        }
        userRepository.delete(user);
        return "Пользователь удалён: " + user;
    }

    @PutMapping("/user/banned/{id}")
    @PreAuthorize("hasAuthority('admin:write')")
    public String bannedUserById(@PathVariable Long id) {
        Log log = new Log();
        log.setWho(String.valueOf(userRepository.findById(id).orElseThrow()));
        log.setEvent("user banned");
        logRepository.save(log);

        User user = userRepository.findById(id).orElseThrow();
        user.setStatus(Status.BANNED);
        Iterable<House> house = houseRepository.findById_landlord(id);
        for (House a : house) {
            a.setStatus(Status.INACTIVELY);
            userRepository.save(user);
        }
        return "Пользователь заблокирован: " + user;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public List<User> userAllGet() {
        return userRepository.findAll();
    }
}