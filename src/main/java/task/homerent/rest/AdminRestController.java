package task.homerent.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import task.homerent.model.House;
import task.homerent.repository.HouseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/house")
public class AdminRestController {

    @Autowired
    private HouseRepository houseRepository;

    public AdminRestController(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:write')")
    public void deleteById(@PathVariable Long id) {
        House house = houseRepository.findById(id).orElseThrow();
        houseRepository.delete(house);
    }
}