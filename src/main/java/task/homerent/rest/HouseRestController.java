package task.homerent.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import task.homerent.dto.ContractDto;
import task.homerent.model.Contract;
import task.homerent.model.House;
import task.homerent.model.Status;
import task.homerent.model.User;
import task.homerent.repository.ContractRepository;
import task.homerent.repository.HouseRepository;
import task.homerent.repository.UserRepository;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/house")
public class HouseRestController {

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private UserRepository userRepository;

    public HouseRestController(HouseRepository houseRepository, ContractRepository contractRepository, UserRepository userRepository) {
        this.houseRepository = houseRepository;
        this.contractRepository = contractRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public House houseInfoId(@PathVariable(value = "id") Long id) {
        Optional<House> house = houseRepository.findById(id);
        List<House> res = new ArrayList<>();
        house.ifPresent(res::add);

        return res.stream().filter(houses -> houses.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('user:read')")
    public Collection<House> houseInfoAll() {
        return houseRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('landlord:write')")
    public House addHouse(@RequestBody House house) {
        house.setStatus(Status.ACTIVE);
        return houseRepository.save(house);
    }

     /**
     * Почему нужно делать по start_date - мы там можем понять, что
     * на тот промежуток, который хочешь арендовать - место может быть свободным.
     * Вариант по проверки наличия арендатора не подходит, достаточно одного атрибута.
     * */
    // Ищем Contract
    // Арендовать квартиру

    @PostMapping("/rent")
    @PreAuthorize("hasAuthority('user:write')")
    public void homeRent(@RequestBody ContractDto contractDto) {
        Contract contract = new Contract();
        Optional<House> house = houseRepository.findById(contractDto.getId_house());
        Optional<User> user = userRepository.findById(contractDto.getId_tenant());

        contract


        contractRepository.save(contract);
    }

    /**
     * Владеет ли кто-то домом
     * Проверка контракта, чтобы дом никто из других не забронировал
     * Чтобы юзер не мог смотреть дом, который уже забронирован
     * Смотреть дома, который ещё не сданы
     * Написать тест на этот метод
     * Транзакцию сделать (в виде таблицы)
     * Таблица, в которую пишутся данные. Типа как логи. То, что дом арендовали, посмотрели
     * По созданию контракта тесты и проверки
     */

    // Дома, которые ещё не сданы
    /*@PostMapping("/rent/info")
    @PreAuthorize("hasAuthority('user:write')")
    public Contract homeInfoRent(@RequestBody Contract contract) {
        Long num = contract.getUser().ge;
        if (contract.getStart_date() == null) {

        }
        return contractRepository.save(contract);
    }*/


}
