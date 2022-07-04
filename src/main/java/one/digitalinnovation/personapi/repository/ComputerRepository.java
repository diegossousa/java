package one.digitalinnovation.personapi.repository;

import one.digitalinnovation.personapi.model.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, String> {
}
