package task.homerent.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import task.homerent.dto.ContractDto;
import task.homerent.dto.UserDto;
import task.homerent.model.House;
import task.homerent.repository.ContractRepository;
import task.homerent.repository.HouseRepository;
import task.homerent.service.ContractService;
import task.homerent.service.HouseService;
import task.homerent.service.UserService;
import task.homerent.web.HouseRestController;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class HouseTest {

    @Autowired
    private UserService userService;
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private HouseService houseService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private HouseRestController houseRestController;
    @Autowired
    private HouseRepository houseRepository;

    @MockBean
    private AuthController authController;

    @MockBean
    private ContractDto contractDto;

    @BeforeEach
    public void AuthLogin() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setEmail("igor@mail.ru");
        userDto.setPassword("igor");
        authController.getLoginPage(userDto);
    }

    @Test
    @WithMockUser(authorities = {"user:write"})
    void ContractHouse() {
        LocalDate endDate = LocalDate.of(2020, 10, 31);
        LocalDate startDate = LocalDate.of(2020, 10, 20);
        ContractDto contractDto = new ContractDto();
        contractDto.setEndDate(endDate);
        contractDto.setStartDate(startDate);
        contractDto.setHouseId((long) 5);
        contractDto.setTenantId((long) 5);
        String freeHouse = houseRestController.findAllByDate(contractDto);
        assertThat(freeHouse).isNotNull();
        assertThat(freeHouse).isEqualTo("Квартира занята");
    }

    @Test
    @WithMockUser(authorities = {"user:read"})
    void ListHouse() {
        List<House> freeHouse = houseService.findfreehouse();
        assertThat(freeHouse).isNotNull();
    }
}
