package pg.decola_tech_avanade_2025.desafios.mustachio.api.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.dto.EditorReservationDto;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.dto.GetReservationDto;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.model.Reservation;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.model.User;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.repository.ReservationRepository;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.repository.UserRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Void> create(@Validated @RequestBody EditorReservationDto newReservationDto) {
        Boolean isReservationValid = reservationRepository.findIsReservationValidBetween(
                newReservationDto.getStartsAt(), newReservationDto.getEndsAt());

        if (!isReservationValid) {
            throw new RuntimeException("Reserva inválida.");
        }

        UUID newReservationId = UUID.randomUUID();
        Reservation newReservation = new Reservation();
        newReservation.setId(newReservationId);

        User reservationOwner = userRepository.findById(newReservationDto.getUserId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        newReservation.setUser(reservationOwner);

        BeanUtils.copyProperties(newReservationDto, newReservation);

        if(newReservation.getEndsAt() == null) {
            newReservation.setEndsAt(newReservation.getStartsAt().plus(Reservation.defaultDuration));
        }

        reservationRepository.save(newReservation);

        return ResponseEntity.created(URI.create(String.format("/reservations/%s", newReservation.getId()))).build();
    }

    @GetMapping
    public ResponseEntity<List<GetReservationDto>> findAll() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<GetReservationDto> reservationDtos = reservations.stream().map(reservation -> {
           GetReservationDto getReservationDto = new GetReservationDto();
           getReservationDto.setUserId(reservation.getUser().getId());
           BeanUtils.copyProperties(reservation, getReservationDto);
           return getReservationDto;
        }).toList();
        return ResponseEntity.ok(reservationDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetReservationDto> findById(@PathVariable UUID id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new RuntimeException("Reserva não encontrada."));
        GetReservationDto getReservationDto = new GetReservationDto();
        getReservationDto.setUserId(reservation.getUser().getId());
        BeanUtils.copyProperties(reservation, getReservationDto);
        return ResponseEntity.ok(getReservationDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable UUID id, @Validated @RequestBody EditorReservationDto newReservationDto) {
        Optional<Reservation> newReservationOptional = reservationRepository.findById(id);


        Reservation newReservation = newReservationOptional.orElseGet(() -> {
            Boolean isReservationValid = reservationRepository.findIsReservationValidBetween(
                    newReservationDto.getStartsAt(), newReservationDto.getEndsAt());

            if (!isReservationValid) {
                throw new RuntimeException("Reserva inválida.");
            }

            Reservation r = new Reservation();
            r.setId(id);
            return r;
        });

        User reservationOwner = userRepository.findById(newReservationDto.getUserId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        newReservation.setUser(reservationOwner);

        BeanUtils.copyProperties(newReservationDto, newReservation);

        if(newReservation.getEndsAt() == null) {
            newReservation.setEndsAt(newReservation.getStartsAt().plus(Reservation.defaultDuration));
        }

        reservationRepository.save(newReservation);

        if (newReservationOptional.isEmpty()) {
            return ResponseEntity.created(URI.create(String.format("/reservations/%s", newReservation.getId()))).build();
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new RuntimeException("Reserva não encontrada."));
        reservationRepository.delete(reservation);
        return ResponseEntity.ok().build();
    }
}
