package pg.decola_tech_avanade_2025.desafios.mustachio.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.dto.ReservationEditorDto;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.model.Reservation;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    @Query("""
            SELECT r
            FROM Reservation r
            WHERE
                (id != ?#{#id})
                AND (
                    (?#{#editorDto.startsAt} >= startsAt AND ?#{#editorDto.startsAt} < endsAt)
                    OR (?#{#editorDto.endsAt} > startsAt AND ?#{#editorDto.endsAt} <= endsAt)
                    OR (?#{#editorDto.startsAt} <= startsAt AND ?#{#editorDto.endsAt} >= endsAt)
                )
            """)
    public List<Reservation> findConflictingReservations(@Param("id") UUID id, @Param("editorDto") ReservationEditorDto editorDto);

    @Query("""
            SELECT r
            FROM Reservation r
            WHERE
                (?#{#editorDto.startsAt} >= startsAt AND ?#{#editorDto.startsAt} < endsAt)
                OR (?#{#editorDto.endsAt} > startsAt AND ?#{#editorDto.endsAt} <= endsAt)
                OR (?#{#editorDto.startsAt} <= startsAt AND ?#{#editorDto.endsAt} >= endsAt)
            """)
    public List<Reservation> findConflictingReservations(@Param("editorDto") ReservationEditorDto editorDto);
}
