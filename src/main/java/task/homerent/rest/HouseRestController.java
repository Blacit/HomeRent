package task.homerent.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import task.homerent.model.House;
import task.homerent.model.Status;
import task.homerent.repository.HouseRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/house")
public class HouseRestController {

    @Autowired
    private HouseRepository houseRepository;

    public HouseRestController(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    // Вывести всю информацию по конкретной квартире
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public House userPostInfo(@PathVariable(value = "id") Long id) {
        Optional<House> house = houseRepository.findById(id);
        List<House> res = new ArrayList<>();
        house.ifPresent(res::add);

        return res.stream().filter(houses -> houses.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Вывести всю информацию о всех квартирах
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('user:read')")
    public Collection<House> userPostInfo() {
        return houseRepository.findAll();
    }

    // Добавить квартиру
    @PostMapping
    @PreAuthorize("hasAuthority('landlord:write')")
    public House create(@RequestBody House house) {
        Status status = Status.ACTIVE;
        house.setStatus(status);
        return houseRepository.save(house);
    }
}
