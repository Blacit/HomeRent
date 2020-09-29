package task.homerent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import task.homerent.model.House;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
}
