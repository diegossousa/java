package one.digitalinnovation.personapi.controllers;

import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.exceptions.PersonNotFoundException;
import one.digitalinnovation.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<PersonDTO> getPeople() {
        return personService.getAll();
    }

    @GetMapping("/{id}")
    public PersonDTO getPersonById(@PathVariable Long id) throws PersonNotFoundException {
        return personService.getPersonById(id);
    }

    @PostMapping("/person")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
        return personService.createPerson(personDTO);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO editPerson(@PathVariable Long id, @RequestBody PersonDTO personDTO) {
        return personService.editPerson(id, personDTO);
    }

    @DeleteMapping("/{id}")
    public MessageResponseDTO deletePersonById(@PathVariable Long id) {
        return personService.deleteById(id);
    }
}
