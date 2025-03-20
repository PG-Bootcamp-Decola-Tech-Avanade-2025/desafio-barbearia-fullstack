package pg.decola_tech_avanade_2025.desafios.mustachio.api.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserResponseDto {
    private UUID id;
    private String username;
    private String password;
}
