package task.homerent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.homerent.model.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
}