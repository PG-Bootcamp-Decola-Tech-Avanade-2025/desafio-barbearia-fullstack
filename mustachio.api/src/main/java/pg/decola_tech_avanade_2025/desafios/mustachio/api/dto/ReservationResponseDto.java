package pg.decola_tech_avanade_2025.desafios.mustachio.api.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ReservationResponseDto {
    private UUID id;
    private UUID userId;
    private LocalDateTime startsAt;
    private LocalDateTime endsAt;
}
