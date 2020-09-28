package task.homerent.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import task.homerent.model.City;
import task.homerent.repository.CityRepository;
import task.homerent.repository.HouseRepository;

import java.util.List;

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
    public List<City> deleteById() {
        return cityRepository.findAll();
    }
}