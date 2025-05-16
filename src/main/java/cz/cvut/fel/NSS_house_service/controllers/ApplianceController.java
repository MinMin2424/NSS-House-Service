/*
 * Created by minmin_tranova on 16.05.2025
 */

package cz.cvut.fel.NSS_house_service.controllers;

import cz.cvut.fel.NSS_house_service.entities.Appliance;
import cz.cvut.fel.NSS_house_service.exceptions.ApplianceNotFoundException;
import cz.cvut.fel.NSS_house_service.services.ApplianceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/appliances")
public class ApplianceController {

    private final ApplianceService applianceService;
    public ApplianceController(ApplianceService applianceService) {
        this.applianceService = applianceService;
    }

    @GetMapping
    public List<Appliance> getAppliances() {
        return applianceService.getAppliances();
    }

    @GetMapping("/{id}")
    public Appliance getApplianceById(@PathVariable Long id) {
        return applianceService.getApplianceById(id)
                .orElseThrow(() ->
                        new ApplianceNotFoundException("Appliance with id " + id + " not found")
                );
    }

    @GetMapping("/room/{roomId}")
    public List<Appliance> getAppliancesByRoomId(@PathVariable Long roomId) {
        return applianceService.getAppliancesByRoomId(roomId);
    }

    @PostMapping
    public Appliance createAppliance(@RequestBody Appliance appliance) {
        return applianceService.createAppliance(appliance);
    }

    @PostMapping("/room/{roomId}/random")
    public Appliance createRandomApplianceInRoom(@PathVariable Long roomId) {
        return applianceService.createRandomAppliance(roomId);
    }
}

