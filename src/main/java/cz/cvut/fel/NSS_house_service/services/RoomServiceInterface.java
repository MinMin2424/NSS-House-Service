/*
 * Created by minmin_tranova on 16.05.2025
 */


package cz.cvut.fel.NSS_house_service.services;

import cz.cvut.fel.NSS_house_service.entities.Room;

import java.util.ArrayList;

public interface RoomServiceInterface {
    ArrayList<Room> GetAllRooms();
    Long SaveRoom(Room newRoom);
    Room GetRoom(Long id);
}

