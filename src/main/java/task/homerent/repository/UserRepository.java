package task.homerent.repository;

import org.springframework.stereotype.Repository;
import task.homerent.model.Role;
import task.homerent.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByRole(Role role);
}