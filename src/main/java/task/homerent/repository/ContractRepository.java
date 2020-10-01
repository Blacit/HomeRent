package task.homerent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.homerent.model.Contract;

import java.time.LocalDate;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    findContractByHouseIdAndEnd_dateAfter(String houseId LocalDate from);
}