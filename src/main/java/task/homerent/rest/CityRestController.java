package task.homerent.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import task.homerent.model.City;
import task.homerent.repository.CityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/city")
public class CityRestController {

    @Autowired
    private CityRepository cityRepository;

    public CityRestController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    // Выводим информацию о городах
    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public List<City> listCity() {
        return cityRepository.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public Optional<City> cityInfo(@PathVariable(value = "id") Long id) {
        return cityRepository.findById(id);
    }
}