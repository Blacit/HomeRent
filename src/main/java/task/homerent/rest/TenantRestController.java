package task.homerent.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import task.homerent.model.*;
import task.homerent.repository.HouseRepository;
import task.homerent.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/tenant")
public class TenantRestController {

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private UserRepository userRepository;

    public TenantRestController(HouseRepository houseRepository, UserRepository userRepository) {
        this.houseRepository = houseRepository;
        this.userRepository = userRepository;
    }

    // TENANT становится LANDLORD, если были квартиры, то активировать их
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
        return "Присвоена роль LANDLORD и активированы квартиры, если ранее были\n" + user;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public List<User> tenantGet() {
        return userRepository.findByRole(Role.TENANT);
    }
}