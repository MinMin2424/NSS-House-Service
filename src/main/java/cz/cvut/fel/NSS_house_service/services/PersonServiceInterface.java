/*
 * Created by minmin_tranova on 16.05.2025
 */


package cz.cvut.fel.NSS_house_service.services;

import cz.cvut.fel.NSS_house_service.entities.Person;

import java.util.List;
import java.util.Optional;

public interface PersonServiceInterface {
    List<Person> getPersonList();
    Optional<Person> getPersonById(Long personId);
    List<Person> getPeopleByRoomId(Long roomId);
    Person createPerson(Person person);
    Person createRandomPerson(Long roomId);
    void setPersonHandlingEvent(Long personId);
    void setPersonFreeById(Long personId);
}

