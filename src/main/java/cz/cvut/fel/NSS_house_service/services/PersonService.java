/*
 * Created by minmin_tranova on 16.05.2025
 */

package cz.cvut.fel.NSS_house_service.services;

import cz.cvut.fel.NSS_house_service.entities.Person;
import cz.cvut.fel.NSS_house_service.entities.PersonType;
import cz.cvut.fel.NSS_house_service.exceptions.PersonNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonService {

    private final List<Person> personList;
    private final AtomicLong idGenerator;
    private final PersonType[] roles;
    private final Random random;

    public PersonService() {
        personList = new ArrayList<>();
        idGenerator = new AtomicLong(1);
        roles = PersonType.values();
        random = new Random();
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public Optional<Person> getPersonById(Long personId) {
        return personList.stream()
                .filter(person -> personId.equals(person.getPersonId()))
                .findFirst();
    }

    public List<Person> getPeopleByRoomId(Long roomId) {
        return personList.stream()
                .filter(person -> roomId.equals(person.getRoomId()))
                .toList();
    }

    public Person createPerson(Person person) {
        Long personId = idGenerator.getAndIncrement();
        person.setPersonId(personId);
        personList.add(person);
        return person;
    }

    public Person createRandomPerson(Long roomId) {
        Person person = new Person();
        PersonType role = roles[random.nextInt(roles.length)];
        person.setRole(role);
        person.setRoomId(roomId);
        person.setBusy(false);
        personList.add(person);
        return person;
    }

    public void setPersonHandlingEvent(Long personId) {
        Person person = getPersonById(personId).orElseThrow(
                () -> new PersonNotFoundException("Person with id " + personId + " not found")
        );
        person.setBusy(true);
    }

    public void setPersonFreeById(Long personId) {
        Person person = getPersonById(personId).orElseThrow(
                () -> new PersonNotFoundException("Person with id " + personId + " not found")
        );
        person.setBusy(false);
    }
}

