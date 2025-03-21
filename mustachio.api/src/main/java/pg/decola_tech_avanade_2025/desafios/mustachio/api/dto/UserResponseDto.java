package pg.decola_tech_avanade_2025.desafios.mustachio.api.dto;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.model.User;

import java.util.UUID;

@Data
public class UserResponseDto {
    private UUID id;
    private String username;
    private String password;

    public static UserResponseDto fromUser(User model) {
        UserResponseDto instance = new UserResponseDto();
        BeanUtils.copyProperties(model, instance);
        return instance;
    }
}
