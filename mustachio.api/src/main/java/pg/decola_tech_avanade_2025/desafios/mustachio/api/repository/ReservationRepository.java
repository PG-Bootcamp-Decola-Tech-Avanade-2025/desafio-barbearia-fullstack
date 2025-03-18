package pg.decola_tech_avanade_2025.desafios.mustachio.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.model.Reservation;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    @Query("""
            SELECT (COUNT(*) = 0)
            FROM Reservation
            WHERE
                (startsAt >= ?1 AND startsAt < ?2)
                OR (endsAt > ?1 AND endsAt <= ?2)
            """)
    public Boolean findIsReservationValidBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
