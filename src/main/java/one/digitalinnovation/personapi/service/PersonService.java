package one.digitalinnovation.personapi.service;

import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.exceptions.PersonNotFoundException;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.model.Person;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonDTO> getAll() {
        List<Person> personList = personRepository.findAll();
        return personList.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO getPersonById(Long id) throws PersonNotFoundException {
        Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
        return personMapper.toDTO(person);
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        String message;
        if (verifyIfExists(personDTO.getId())) {
            message = "Person Already Exists With ID: " + personDTO.getId();
        } else {
            Person savedPerson = personRepository.save(personMapper.toModel(personDTO));
            message = "Created Person with ID: " + savedPerson.getId();
        }
        return responseMessageDTO(message);
    }

    public MessageResponseDTO editPerson(PersonDTO personDTO) {
        String message;
        if (verifyIfExists(personDTO.getId())) {
            message = "Person with ID " + personDTO.getId() + " do not exists";
        } else {
            Person personToEdit = personMapper.toModel(personDTO);
            personRepository.save(personToEdit);
            message = "Modified Person with ID: " + personDTO.getId();
        }
        return responseMessageDTO(message);
    }

    public MessageResponseDTO deleteById(Long id) {
        String message;
        if (verifyIfExists(id)){
            personRepository.deleteById(id);
            message = "Person with ID: " + id + " deleted";
        }else {
            message = "Person with ID " + id + " do not exists";
        }
            return responseMessageDTO(message);
    }

    private MessageResponseDTO responseMessageDTO(String message) {
        return MessageResponseDTO
                .builder()
                .message(message)
                .build();
    }

    private boolean verifyIfExists(Long id) {
        return personRepository.existsById(id);
    }
}
