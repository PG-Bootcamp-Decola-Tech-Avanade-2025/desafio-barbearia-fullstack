package pg.decola_tech_avanade_2025.desafios.mustachio.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Reservation {
    public static final Duration defaultDuration = Duration.ofHours(1L);

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    private LocalDateTime startsAt;
    private LocalDateTime endsAt;
}
