package task.homerent.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import task.homerent.model.*;
import task.homerent.repository.ContractRepository;
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

    // Присвоить пользователю TENANT и деактивировать квартиры, если были
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('landlord:write')")
    public void TenantPostAdd(@PathVariable(value = "id") Long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setRole(Role.TENANT);
        House house = houseRepository.findById(id).orElseThrow();
        house.setStatus(Status.INACTIVELY);
    }

    // Выводим весь список арендаторов
    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public List<User> landlordGet() {
        return userRepository.findByRole(Role.TENANT);
    }
}