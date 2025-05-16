/*
 * Created by minmin_tranova on 16.05.2025
 */

package cz.cvut.fel.NSS_house_service.services;

import cz.cvut.fel.NSS_house_service.entities.Appliance;
import cz.cvut.fel.NSS_house_service.entities.ApplianceType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ApplianceService {

    private final List<Appliance> appliances;
    private final ApplianceType[] applianceTypes;
    private final AtomicLong idGenerator;
    private final Random random;

    public ApplianceService() {
        appliances = new ArrayList<>();
        applianceTypes = ApplianceType.values();
        idGenerator = new AtomicLong(1);
        random = new Random();
    }

    public List<Appliance> getAppliances() {
        return appliances;
    }

    public Optional<Appliance> getApplianceById(Long applianceId) {
        return appliances.stream()
                .filter(appliance -> applianceId.equals(appliance.getApplianceId()))
                .findFirst();
    }

    public List<Appliance> getAppliancesByRoomId(Long roomId) {
        return appliances.stream()
                .filter(appliance -> roomId.equals(appliance.getRoomId()))
                .toList();
    }

    public Appliance createAppliance(Appliance appliance) {
        Long applianceId = idGenerator.getAndIncrement();
        appliance.setApplianceId(applianceId);
        appliances.add(appliance);
        return appliance;
    }

    public Appliance createRandomAppliance(Long roomId) {
        Appliance appliance = new Appliance();

        ApplianceType randomApplianceType = applianceTypes[random.nextInt(applianceTypes.length)];
        String brand = "Brand-" + random.nextInt(1000);
        LocalDate productionDate = LocalDate.now()
                .minusYears(random.nextInt(10))
                .minusMonths(random.nextInt(12));
        int state = random.nextInt(3);

        appliance.setBrand(brand);
        appliance.setApplianceType(randomApplianceType);
        appliance.setProductionDate(productionDate);
        appliance.setState(state);
        appliance.setRoomId(Objects.requireNonNullElseGet(roomId, () -> random.nextLong(10) + 1));

        return createAppliance(appliance);
    }
}

