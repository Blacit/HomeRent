package task.homerent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import task.homerent.model.Contract;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    @Query(value = "select * from contract WHERE house_id =:houseId and start_date >= cast(:startDate as date) and start_date <= cast(:endDate as date) + interval '1' day", nativeQuery = true)
    List<Contract> findAllByDate(@Param("houseId") Long houseId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}