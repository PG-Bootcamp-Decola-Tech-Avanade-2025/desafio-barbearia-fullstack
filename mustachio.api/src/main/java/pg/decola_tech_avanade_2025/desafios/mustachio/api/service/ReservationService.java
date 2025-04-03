package pg.decola_tech_avanade_2025.desafios.mustachio.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.dto.ReservationEditorDto;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.dto.ReservationResponseDto;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.exception_handler.ReservationConflictException;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.exception_handler.ResourceNotFoundException;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.model.Reservation;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.model.User;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.repository.ReservationRepository;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    public boolean reservationExistsById(UUID id) {
        return reservationRepository.existsById(id);
    }

    public ReservationResponseDto createReservation(ReservationEditorDto editorDto) throws ResourceNotFoundException, ReservationConflictException {
         User owner = userRepository.findById(editorDto.getUserId()).orElseThrow(() -> new ResourceNotFoundException("users", editorDto.getUserId()));

        if (editorDto.getEndsAt() == null) editorDto.setEndsAt(editorDto.getStartsAt().plus(Reservation.defaultDuration));

        List<Reservation> conflictingReservations = reservationRepository.findConflictingReservations(editorDto);
        if (!conflictingReservations.isEmpty()) throw new ReservationConflictException(editorDto, conflictingReservations);

        Reservation newReservation = new Reservation();
        BeanUtils.copyProperties(editorDto, newReservation);
        newReservation.setUser(owner);

        reservationRepository.save(newReservation);

        return ReservationResponseDto.fromReservation(newReservation);
    }

    public List<ReservationResponseDto> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream().map(ReservationResponseDto::fromReservation).toList();
    }

    public ReservationResponseDto getReservationById(UUID id) throws ResourceNotFoundException {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("reservations", id));
        return ReservationResponseDto.fromReservation(reservation);
    }

    public ReservationResponseDto updateReservation(UUID id, ReservationEditorDto editorDto) throws ResourceNotFoundException, ReservationConflictException {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("reservations", id));

        if (editorDto.getEndsAt() == null) editorDto.setEndsAt(editorDto.getStartsAt().plus(Reservation.defaultDuration));

        List<Reservation> conflictingReservations = reservationRepository.findConflictingReservations(id, editorDto);
        if (!conflictingReservations.isEmpty()) throw new ReservationConflictException(editorDto, conflictingReservations);

        BeanUtils.copyProperties(editorDto, reservation);

        if (editorDto.getUserId() != reservation.getUser().getId()) {
            User owner = userRepository.findById(editorDto.getUserId()).orElseThrow(() -> new ResourceNotFoundException("users", editorDto.getUserId()));
            reservation.setUser(owner);
        }

        reservationRepository.save(reservation);

        return ReservationResponseDto.fromReservation(reservation);
    }

    public void deleteReservationById(UUID id) {
        reservationRepository.deleteById(id);
    }
}
