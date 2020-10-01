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
@RequestMapping("/tenant")
public class TenantRestController {

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LogRepository logRepository;

    public TenantRestController(HouseRepository houseRepository, UserRepository userRepository, LogRepository logRepository) {
        this.houseRepository = houseRepository;
        this.userRepository = userRepository;
        this.logRepository = logRepository;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public String tenantAdd(@PathVariable(value = "id") Long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setRole(Role.LANDLORD);
        Iterable<House> house = houseRepository.findById_landlord(id);
        for (House a : house) {
            a.setStatus(Status.ACTIVE);
            userRepository.save(user);
        }

        Log log = new Log();
        log.setWho(String.valueOf(userRepository.findById(id).orElseThrow()));
        log.setEvent("update tenant");
        logRepository.save(log);
        return "Присвоена роль LANDLORD и активированы квартиры, если ранее были подключены\n" + user;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public List<User> tenantGet() {
        return userRepository.findByRole(Role.TENANT);
    }
}