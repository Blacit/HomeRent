package task.homerent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import task.homerent.dto.UserDto;
import task.homerent.model.Role;
import task.homerent.model.Status;
import task.homerent.model.User;
import task.homerent.repository.UserRepository;


@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User addUser(UserDto accountDto) {
        User user = new User();
        user.setFirstName(accountDto.getFirst_name());
        user.setLastName(accountDto.getLast_name());

        // Шифруем и сохраняем
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        user.setRole(Role.TENANT);
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);

        return user;
    }
}