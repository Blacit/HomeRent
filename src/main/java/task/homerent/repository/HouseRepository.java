package task.homerent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import task.homerent.model.House;

import java.util.List;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
    @Query(value = "select * from house where id_landlord = :id_landlord ", nativeQuery = true)
    List<House> findById_landlord(@Param("id_landlord")Long id_landlord);
}
