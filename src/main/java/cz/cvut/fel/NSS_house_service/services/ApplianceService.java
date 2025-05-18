/*
 * Created by minmin_tranova on 09.05.2025
 */

package cz.cvut.fel.NSS_house_service.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import cz.cvut.fel.NSS_house_service.entities.Appliance;
import cz.cvut.fel.NSS_house_service.entities.ApplianceType;

@Service
public class ApplianceService implements ApplianceServiceInterface {

    @Getter
    private final List<Appliance> appliances;
    private final ApplianceType[] applianceTypes;
    private final AtomicLong idGenerator;
    private final Random random;

    @Autowired
    private KafkaTemplate<String, String> kafka;

    public ApplianceService() {
        appliances = new ArrayList<>();
        applianceTypes = ApplianceType.values();
        idGenerator = new AtomicLong(1);
        random = new Random();
    }

    /*public List<Appliance> getAppliances() {
        return appliances;
    }*/

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
        kafka.send("log.created", String.format("Creating appliance with id: %d in room: %d", applianceId, appliance.getRoomId()));
        return appliance;
    }

    public Appliance createRandomAppliance(Long roomId) {
        ApplianceType randomApplianceType = applianceTypes[random.nextInt(applianceTypes.length)];
        String brand = "Brand-" + random.nextInt(1000);
        LocalDate productionDate = LocalDate.now()
                .minusYears(random.nextInt(10))
                .minusMonths(random.nextInt(12));
        int state = random.nextInt(3);

        Appliance appliance = Appliance.builder()
                .brand(brand)
                .applianceType(randomApplianceType)
                .productionDate(productionDate)
                .state(state)
                .roomId(Objects.requireNonNullElseGet(roomId, () -> random.nextLong(10) + 1))
                .build();

        appliance = createAppliance(appliance);
        kafka.send("log.created", String.format("Creating random appliance with id: %d for room: %d", appliance.getApplianceId(), roomId));
        return appliance;
    }
}
