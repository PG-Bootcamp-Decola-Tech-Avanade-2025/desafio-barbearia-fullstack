package pg.decola_tech_avanade_2025.desafios.mustachio.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.model.User;

@Data
public class UserEditorDto {
    @NotBlank(message = "Property must not be null or empty.")
    private String username;

    @NotBlank(message = "Property must not be null or empty.")
    private String password;

    public static UserEditorDto fromUser(User model) {
        UserEditorDto instance = new UserEditorDto();
        BeanUtils.copyProperties(model, instance);
        return instance;
    }
}
