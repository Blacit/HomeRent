package task.homerent.controller;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import task.homerent.dto.UserDto;
import task.homerent.model.Role;
import task.homerent.model.User;
import task.homerent.repository.UserRepository;
import task.homerent.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
class RegistrationControllerTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;


    @Test
    void registerNewUserAccount() {
        UserDto userDto = new UserDto();

        User user = userService.addUser(userDto);

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }
}