package one.digitalinnovation.personapi.service;

import one.digitalinnovation.personapi.model.Computer;
import one.digitalinnovation.personapi.repository.ComputerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class ComputerService {

    private final ComputerRepository computerRepository;

    public ComputerService(ComputerRepository computerRepository) {
        this.computerRepository = computerRepository;
    }

    public Stream<?> getAll() {
        List<Computer> all = computerRepository.findAll();
        if (all.size() == 0) {
            return Stream.empty();
        }
        return Stream.of(all);
    }

    public List<Computer> searchComputerByHostname(String hostname) {
        return computerRepository.findAll();
    }

    public Computer findComputerByHostname(String hostname) {
        return computerRepository.findById(hostname).orElseThrow();
    }

    public Computer createComputer(Computer computer) {
        return computerRepository.save(computer);
    }

    public Computer updateComputer(Computer computer) {
        return computerRepository.save(computer);
    }

    public void deleteComputer(Computer computer) {
        computerRepository.delete(computer);
    }
}
