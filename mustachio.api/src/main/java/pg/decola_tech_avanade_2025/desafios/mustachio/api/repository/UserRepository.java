package pg.decola_tech_avanade_2025.desafios.mustachio.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.model.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
