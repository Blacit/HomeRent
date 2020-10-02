package task.homerent.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import task.homerent.dto.ContractDto;
import task.homerent.model.Contract;
import task.homerent.model.House;
import task.homerent.model.Status;
import task.homerent.repository.ContractRepository;
import task.homerent.repository.HouseRepository;
import task.homerent.repository.UserRepository;
import task.homerent.service.ContractService;
import task.homerent.service.HouseService;
import task.homerent.service.UserService;

import java.util.*;

@RestController
@RequestMapping("/house")
public class HouseRestController {

    private final HouseService houseService;
    private final UserService userService;
    private final ContractService contractService;

    @Autowired
    public HouseRestController(HouseService houseService, UserService userService, ContractService contractService) {
        this.houseService = houseService;
        this.userService = userService;
        this.contractService = contractService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public House houseInfoId(@PathVariable(value = "id") Long id) {
        Optional<House> house = houseService.findById(id);
        List<House> res = new ArrayList<>();
        house.ifPresent(res::add);

        return res.stream().filter(houses -> houses.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('user:read')")
    public Collection<House> houseInfoAll() {
        return houseService.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('landlord:write')")
    public House addHouse(@RequestBody House house) {
        house.setStatus(Status.ACTIVE);
        return houseService.save(house);
    }

    @PostMapping("/rent") // не работает
    @PreAuthorize("hasAuthority('user:write')")
    public void homeRent(@RequestBody ContractDto contractDto) {
        List<Contract> con = contractService.findContractByHouseIdAndEndDateAfter(contractDto.getHouseId(), contractDto.getEndDate());
        for(Contract co : con) {
            System.out.println(co);
        }
    }
}