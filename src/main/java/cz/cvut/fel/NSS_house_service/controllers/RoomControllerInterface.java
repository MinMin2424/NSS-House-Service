/*
 * Created by minmin_tranova on 16.05.2025
 */


package cz.cvut.fel.NSS_house_service.controllers;

import cz.cvut.fel.NSS_house_service.entities.Room;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoomControllerInterface {
    ResponseEntity<Long> saveProduct(Room room);
    List<Room> getAllProducts();
    ResponseEntity<Room> getProductById(Long id);
}

