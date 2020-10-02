package task.homerent.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import task.homerent.model.City;
import task.homerent.service.CityService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/city")
public class CityRestController {

    private final CityService cityService;

    @Autowired
    public CityRestController(CityService cityService) {
        this.cityService = cityService;
    }


    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public List<City> cityGet() {
        return cityService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public Optional<City> cityInfoId(@PathVariable(value = "id") Long id) {
        return cityService.findById(id);
    }
}