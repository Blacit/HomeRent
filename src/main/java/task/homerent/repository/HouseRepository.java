package task.homerent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import task.homerent.model.Contract;
import task.homerent.model.House;

import java.util.List;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
    @Query(value = "select * from house where landlord_id = :landlord_id ", nativeQuery = true)
    List<House> findById_landlord(@Param("landlord_id")Long landlordId);
    @Query(value = "SELECT house.*, contract.house_id FROM house LEFT JOIN contract on contract.house_id = house.id WHERE contract.house_id IS NULL", nativeQuery = true)
    List<House> findfreehouse();
}
