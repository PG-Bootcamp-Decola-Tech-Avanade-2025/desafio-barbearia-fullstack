package pg.decola_tech_avanade_2025.desafios.mustachio.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "tb_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    List<Reservation> reservations;

    private String username;
    private String password;
}
