/*
 * Created by minmin_tranova on 16.05.2025
 */

package cz.cvut.fel.NSS_house_service.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Builder
public class Appliance {

    private Long applianceId;
    private String brand;
    private LocalDate productionDate;
    private int state;
    private ApplianceType applianceType;
    private Long roomId;

}
