package task.homerent.controller;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import task.homerent.dto.UserDto;
import task.homerent.model.Role;
import task.homerent.model.User;
import task.homerent.repository.ContractRepository;
import task.homerent.repository.HouseRepository;
import task.homerent.repository.UserRepository;
import task.homerent.service.ContractService;
import task.homerent.service.HouseService;
import task.homerent.service.UserService;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
class RegistrationControllerTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private ContractService contractService;

    @MockBean
    private HouseService houseService;

    @Autowired
    public RegistrationControllerTest(UserService userService) {
        this.userService = userService;
    }

    @Test
    void registerNewUserAccount() {
        UserDto userDto = new UserDto();

        User user = userService.addUser(userDto);

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
        Mockito.verify(contractService, Mockito.times(1))
                .findAllByDate(
                        ArgumentMatchers.anyLong(),
                        ArgumentMatchers.any(LocalDate.class),
                        ArgumentMatchers.any(LocalDate.class)
                );
        Mockito.verify(houseService, Mockito.times(1)).findfreehouse();

    }
}