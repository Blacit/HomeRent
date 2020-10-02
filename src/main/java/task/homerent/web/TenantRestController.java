package task.homerent.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import task.homerent.model.*;
import task.homerent.service.HouseService;
import task.homerent.service.LogService;
import task.homerent.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/tenant")
public class TenantRestController {

    private final HouseService houseService;
    private final UserService userService;
    private final LogService logService;

    @Autowired
    public TenantRestController(HouseService houseService, UserService userService, LogService logService) {
        this.houseService = houseService;
        this.userService = userService;
        this.logService = logService;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public String tenantAdd(@PathVariable(value = "id") Long id) {
        User user = userService.findById(id);
        user.setRole(Role.LANDLORD);
        Iterable<House> house = houseService.findById_landlord(id);
        for (House a : house) {
            a.setStatus(Status.ACTIVE);
            userService.save(user);
        }

        Log log = new Log();
        log.setWho(String.valueOf(userService.findById(id)));
        log.setEvent("update tenant");
        logService.save(log);
        return "Присвоена роль LANDLORD и активированы квартиры, если ранее были подключены\n" + user;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public List<User> tenantGet() {
        return userService.findByRole(Role.TENANT);
    }
}