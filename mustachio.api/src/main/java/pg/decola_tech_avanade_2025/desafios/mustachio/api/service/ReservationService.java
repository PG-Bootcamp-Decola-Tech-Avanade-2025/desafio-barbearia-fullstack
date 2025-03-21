package pg.decola_tech_avanade_2025.desafios.mustachio.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.dto.ReservationEditorDto;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.dto.ReservationResponseDto;
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

    public ReservationResponseDto createReservation(ReservationEditorDto editorDto) {
         User owner = userRepository.findById(editorDto.getUserId()).orElseThrow(RuntimeException::new);

        if (editorDto.getEndsAt() == null) editorDto.setEndsAt(editorDto.getStartsAt().plus(Reservation.defaultDuration));

        boolean isReservationDateValid = reservationRepository.isReservationDateValid(editorDto);
        if (!isReservationDateValid) throw new RuntimeException();

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

    public ReservationResponseDto getReservationById(UUID id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(RuntimeException::new);
        return ReservationResponseDto.fromReservation(reservation);
    }

    public ReservationResponseDto updateReservation(UUID id, ReservationEditorDto editorDto) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(RuntimeException::new);

        if (editorDto.getEndsAt() == null) editorDto.setEndsAt(editorDto.getStartsAt().plus(Reservation.defaultDuration));

        boolean isReservationDateValid = reservationRepository.isReservationDateValid(id, editorDto);
        if (!isReservationDateValid) throw new RuntimeException();

        BeanUtils.copyProperties(editorDto, reservation);

        if (editorDto.getUserId() != reservation.getUser().getId()) {
            User owner = userRepository.findById(editorDto.getUserId()).orElseThrow(RuntimeException::new);
            reservation.setUser(owner);
        }

        reservationRepository.save(reservation);

        return ReservationResponseDto.fromReservation(reservation);
    }

    public void deleteReservationById(UUID id) {
        reservationRepository.deleteById(id);
    }
}
