package pg.decola_tech_avanade_2025.desafios.mustachio.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.dto.ReservationEditorDto;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.dto.ReservationResponseDto;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.exception_handler.ReservationConflictException;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.exception_handler.ResourceNotFoundException;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.service.ReservationService;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody ReservationEditorDto editorDto) throws ReservationConflictException, ResourceNotFoundException {
        ReservationResponseDto responseDto = reservationService.createReservation(editorDto);
        return ResponseEntity.created(
                URI.create("/reservations/" + responseDto.getId())
        ).build();
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponseDto>> findAll() {
        List<ReservationResponseDto> responseDtos = reservationService.getAllReservations();
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDto> findById(@PathVariable UUID id) throws ResourceNotFoundException {
        ReservationResponseDto responseDto = reservationService.getReservationById(id);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> createOrUpdate(@PathVariable UUID id, @Valid @RequestBody ReservationEditorDto editorDto) throws ReservationConflictException, ResourceNotFoundException {
        if (reservationService.reservationExistsById(id)) {
            reservationService.updateReservation(id, editorDto);
            return ResponseEntity.ok().build();
        } else {
            ReservationResponseDto responseDto = reservationService.createReservation(editorDto);
            return ResponseEntity.created(
                    URI.create("/reservations/" + responseDto.getId())
            ).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        reservationService.deleteReservationById(id);
        return ResponseEntity.ok().build();
    }
}
