package pg.decola_tech_avanade_2025.desafios.mustachio.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EditorUserDto {
    @NotBlank(message = "Username não deve ser vazio.")
    private String username;

    @NotBlank(message = "Password não deve ser vazio.")
    private String password;
}
