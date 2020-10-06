package task.homerent.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import task.homerent.dto.UserDto;
import task.homerent.model.User;
import task.homerent.repository.UserRepository;
import task.homerent.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
class RegistrationAndAuthControllerTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationAndAuthControllerTest(UserService userService) {
        this.userService = userService;
    }

    @MockBean
    AuthController authController;

    @Test
    public void AuthLogin() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setEmail("igor@mail.ru");
        userDto.setPassword("igor");
        authController.getLoginPage(userDto);
    }

    @Test
    void registerNewUserAccount() {
        UserDto userDto = new UserDto();
        User user = userService.addUser(userDto);
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }
}