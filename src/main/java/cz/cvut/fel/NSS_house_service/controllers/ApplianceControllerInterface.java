/*
 * Created by minmin_tranova on 16.05.2025
 */

package cz.cvut.fel.NSS_house_service.controllers;

import cz.cvut.fel.NSS_house_service.entities.Appliance;

import java.util.List;

public interface ApplianceControllerInterface {
    List<Appliance> getAppliances();
    Appliance getApplianceById(Long id);
    List<Appliance> getAppliancesByRoomId(Long roomId);
    Appliance createAppliance(Appliance appliance);
    Appliance createRandomApplianceInRoom(Long roomId);
}

