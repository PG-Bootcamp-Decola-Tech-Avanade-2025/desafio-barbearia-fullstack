package pg.decola_tech_avanade_2025.desafios.mustachio.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.model.Reservation;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ReservationEditorDto {
    @NotNull(message = "Property must not be null or empty.")
    private UUID userId;

    @NotNull(message = "Property must not be null or empty.")
    private LocalDateTime startsAt;

    private LocalDateTime endsAt;

    public static ReservationEditorDto fromReservation(Reservation model) {
        ReservationEditorDto instance = new ReservationEditorDto();
        BeanUtils.copyProperties(model, instance);
        return instance;
    }
}
