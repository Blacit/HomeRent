package task.homerent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.homerent.model.House;
import task.homerent.model.Log;
import task.homerent.repository.LogRepository;

import java.util.Optional;

@Service
public class LogService {

    private final LogRepository logRepository;

    @Autowired
    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }


    public Optional<Log> findById(Long id){
        return logRepository.findById(id);
    }

    public Log save(Log log){
        return logRepository.save(log);
    }
}
