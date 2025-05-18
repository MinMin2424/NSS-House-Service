/*
 * Created by minmin_tranova on 16.05.2025
 */


package cz.cvut.fel.NSS_house_service.services;

import cz.cvut.fel.NSS_house_service.entities.Appliance;

import java.util.List;
import java.util.Optional;

public interface ApplianceServiceInterface {
    List<Appliance> getAppliances();
    Optional<Appliance> getApplianceById(Long applianceId);
    List<Appliance> getAppliancesByRoomId(Long roomId);
    Appliance createAppliance(Appliance appliance);
    Appliance createRandomAppliance(Long roomId);
}

