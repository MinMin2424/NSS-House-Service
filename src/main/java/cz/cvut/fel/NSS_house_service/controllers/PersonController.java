/*
 * Created by minmin_tranova on 16.05.2025
 */

package cz.cvut.fel.NSS_house_service.controllers;

import cz.cvut.fel.NSS_house_service.entities.Person;
import cz.cvut.fel.NSS_house_service.exceptions.PersonNotFoundException;
import cz.cvut.fel.NSS_house_service.services.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController implements PersonControllerInterface {

    private final PersonService personService;
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getAllPeople() {
        return personService.getPersonList();
    }

    @GetMapping("/{personId}")
    public Person getPersonById(@PathVariable Long personId) {
        return personService.getPersonById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person with id " + personId + " not found"));
    }

    @GetMapping("/room/{roomId}")
    public List<Person> getPeopleByRoomId(@PathVariable Long roomId) {
        return personService.getPeopleByRoomId(roomId);
    }

    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return personService.createPerson(person);
    }

    @PostMapping("/room/{roomId}/random")
    public Person createRandomPersonInRoom(@PathVariable Long roomId) {
        return personService.createRandomPerson(roomId);
    }

    @PutMapping("/{personId}/handlingEvent")
    public void setPersonHandlingEvent(@PathVariable Long personId) {
        personService.setPersonHandlingEvent(personId);
    }

    @PutMapping("/{personId}/free")
    public void setPersonFree(@PathVariable Long personId) {
        personService.setPersonFreeById(personId);
    }

}

