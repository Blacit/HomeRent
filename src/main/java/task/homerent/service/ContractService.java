package task.homerent.service;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.homerent.model.Contract;
import task.homerent.repository.ContractRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    public List<Contract> findAllByDate(Long house_id, LocalDate endDate, LocalDate startDate) {
        return contractRepository.findAllByDate(house_id, endDate, startDate);
    }

    public void save(Contract con) {
        contractRepository.save(con);
    }

}
