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

@RestController
@RequestMapping("/tenant")
public class TenantRestController {

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private UserRepository userRepository;

    public TenantRestController(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    // Присвоить пользователю TENANT и удалить его квартиры, если бы
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('landlord:write')")
    public void TenantPostAdd(@PathVariable(value = "id") Long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setRole(Role.TENANT);
        House house = houseRepository.findById(id).orElseThrow();
        house.setStatus(Status.INACTIVELY);
    }
}