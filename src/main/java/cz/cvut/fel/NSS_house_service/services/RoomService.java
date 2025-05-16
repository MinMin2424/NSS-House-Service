/*
 * Created by minmin_tranova on 16.05.2025
 */

package cz.cvut.fel.NSS_house_service.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import cz.cvut.fel.NSS_house_service.entities.Room;

@Service
public class RoomService {
    private final ArrayList<Room> savedRooms;
    public RoomService() {
        savedRooms = new ArrayList<>();
    }

    public ArrayList<Room> GetAllRooms(){
        return savedRooms;
    }

    public Long SaveRoom(Room newRoom){
        Long newId = GetNextId();
        newRoom.setId(newId);
        savedRooms.add(newRoom);
        return newId;
    }

    public Room GetRoom(Long id){
        return savedRooms.stream().filter(r -> r.getId() == id).findFirst().orElse(null);
    }

    private Long GetNextId(){
        return savedRooms.stream().mapToLong(Room::getId).max().orElse(0) + 1;
    }
}
