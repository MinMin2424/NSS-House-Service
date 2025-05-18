/*
 * Created by minmin_tranova on 16.05.2025
 */


package cz.cvut.fel.NSS_house_service.controllers;

import cz.cvut.fel.NSS_house_service.entities.Person;

import java.util.List;

public interface PersonControllerInterface {
    List<Person> getAllPeople();
    Person getPersonById(Long personId);
    List<Person> getPeopleByRoomId(Long roomId);
    Person createPerson(Person person);
    Person createRandomPersonInRoom(Long roomId);
    void setPersonHandlingEvent(Long personId);
    void setPersonFree(Long personId);
}

