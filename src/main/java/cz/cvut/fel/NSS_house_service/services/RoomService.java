package cz.cvut.fel.NSS_house_service.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import cz.cvut.fel.NSS_house_service.entities.Room;

@Service
public class RoomService implements RoomServiceInterface {
    private final ArrayList<Room> savedRooms;

    @Autowired
    private KafkaTemplate<String, String> kafka;

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
        kafka.send("log.created", String.format("Creating room with id: %d", newId));
        return newId;
    }

    public Room GetRoom(Long id){
        return savedRooms.stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null);
    }

    private Long GetNextId(){
        return savedRooms.stream().mapToLong(Room::getId).max().orElse(0) + 1;
    }
}
