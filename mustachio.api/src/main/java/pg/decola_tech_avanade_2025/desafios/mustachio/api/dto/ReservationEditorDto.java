package pg.decola_tech_avanade_2025.desafios.mustachio.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ReservationEditorDto {
    @NotNull(message = "UserId não deve ser vazio.")
    private UUID userId;

    @NotNull(message = "StartsAt não deve ser vazio.")
    private LocalDateTime startsAt;

    private LocalDateTime endsAt;
}
