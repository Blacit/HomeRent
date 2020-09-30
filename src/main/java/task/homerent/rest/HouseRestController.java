package task.homerent.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import task.homerent.model.Contract;
import task.homerent.model.House;
import task.homerent.model.Status;
import task.homerent.repository.ContractRepository;
import task.homerent.repository.HouseRepository;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/house")
public class HouseRestController {

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private ContractRepository contractRepository;

    public HouseRestController(HouseRepository houseRepository, ContractRepository contractRepository) {
        this.houseRepository = houseRepository;
        this.contractRepository = contractRepository;
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
    public void homeRent(@RequestBody Contract contract) {
        System.out.println("ID арендатора");
        System.out.println(contract.getUser().getId());
        System.out.println("Начало аренды");
        int dates = Integer.valueOf(String.valueOf(contract.getStart_date()));
        //LocalDate date = new LocalDate(dates); //- высвечивается ошибка LocalDate(int, int, int) has private access in 'java.time.LocalDate'`
        //System.out.println(date);
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
