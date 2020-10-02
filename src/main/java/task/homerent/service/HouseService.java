package task.homerent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.homerent.model.House;
import task.homerent.repository.HouseRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class HouseService {

    private final HouseRepository houseRepository;

    @Autowired
    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    public Optional<House> findById(Long id){
        return houseRepository.findById(id);
    }

    public void delete(House house) {
        houseRepository.delete(house);
    }

    public Iterable<House> findById_landlord(Long id) {
        return houseRepository.findById_landlord(id);
    }

    public Collection<House> findAll() {
        return houseRepository.findAll();
    }

    public House save(House house) {
        return houseRepository.save(house);
    }
}
