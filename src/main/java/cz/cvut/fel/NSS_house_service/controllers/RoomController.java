/*
 * Created by minmin_tranova on 16.05.2025
 */

package cz.cvut.fel.NSS_house_service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.cvut.fel.NSS_house_service.entities.Room;
import cz.cvut.fel.NSS_house_service.services.RoomService;

@RestController
@RequestMapping
public class RoomController implements RoomControllerInterface {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/room")
    public ResponseEntity<Long> saveProduct(@RequestBody Room room) {
        Long roomId = roomService.SaveRoom(room);
        return ResponseEntity.ok(roomId);
    }

    @GetMapping("/rooms")
    public List<Room> getAllProducts() {
        return roomService.GetAllRooms();
    }

    @GetMapping("/room/{id}")
    public ResponseEntity<Room> getProductById(@PathVariable Long id) {
        Room room = roomService.GetRoom(id);
        if(room == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(room);
        }
    }
}

