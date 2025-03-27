package pg.decola_tech_avanade_2025.desafios.mustachio.api.exception_handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.dto.ReservationEditorDto;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.dto.ReservationResponseDto;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.model.Reservation;

import java.util.List;

@AllArgsConstructor
@Getter
public class ReservationConflictException extends Exception{
    private ReservationEditorDto editorDto;
    private List<Reservation> conflictingReservationDtos;
    private String message;

    public ReservationConflictException(ReservationEditorDto editorDto, List<Reservation> conflictingReservationDtos) {
        this(editorDto, conflictingReservationDtos, null);
    }

    public List<ReservationResponseDto> getConflictingReservationDtos() {
        return conflictingReservationDtos.stream().map(ReservationResponseDto::fromReservation).toList();
    }
}
