package pg.decola_tech_avanade_2025.desafios.mustachio.api.dto;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.model.Reservation;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ReservationResponseDto {
    private UUID id;
    private UUID userId;
    private LocalDateTime startsAt;
    private LocalDateTime endsAt;

    public static ReservationResponseDto fromReservation(Reservation model) {
        ReservationResponseDto instance = new ReservationResponseDto();
        BeanUtils.copyProperties(model, instance);
        instance.setUserId(model.getUser().getId());
        return instance;
    }
}
