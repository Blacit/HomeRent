package task.homerent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.homerent.model.Contract;
import task.homerent.repository.ContractRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class ContractService {

    private final ContractRepository contractRepository;

    @Autowired
    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public Contract findById(Long id){
        return contractRepository.findById(id).orElseThrow();
    }

    public List<Contract> findContractByHouseIdAndEndDateAfter(Long house_id, LocalDate end_Date) {
        return contractRepository.findContractByHouseIdAndEndDateAfter(house_id, end_Date);
    }

    public void save(Contract con) {
        contractRepository.save(con);
    }

}
