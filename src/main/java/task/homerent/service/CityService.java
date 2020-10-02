package task.homerent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.homerent.model.City;
import task.homerent.repository.CityRepository;

import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City findById(Long id){
        return cityRepository.findById(id).orElseThrow();
    }

    public List<City> findAll() {
        return cityRepository.findAll();
    }
}
