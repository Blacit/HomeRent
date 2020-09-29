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
@RequestMapping("/admin/user")
public class AdminRestController {

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private UserRepository userRepository;

    public AdminRestController(HouseRepository houseRepository, UserRepository userRepository) {
        this.houseRepository = houseRepository;
        this.userRepository = userRepository;
    }

    // Удаляем квартиру по ID
    @DeleteMapping("/house/{id}")
    @PreAuthorize("hasAuthority('admin:write')")
    public void deleteHouseById(@PathVariable Long id) {
        House house = houseRepository.findById(id).orElseThrow();
        houseRepository.delete(house);
    }

    // Удаляем пользователя по ID
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:write')")
    public void deleteUserById(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
    }

    // Блокируем пользователя по ID
    @PostMapping("/banned/{id}")
    @PreAuthorize("hasAuthority('admin:write')")
    public void bannedUserById(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setStatus(Status.BANNED);
        House house = houseRepository.findById(id).orElseThrow();
        house.setStatus(Status.INACTIVELY);
    }
}