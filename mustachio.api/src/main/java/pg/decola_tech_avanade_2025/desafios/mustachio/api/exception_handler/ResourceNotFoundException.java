package pg.decola_tech_avanade_2025.desafios.mustachio.api.exception_handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResourceNotFoundException extends Exception {
    private String resourceName;
    private Object resourceId;
    private String message;

    public ResourceNotFoundException(String resourceName, Object resourceId) {
        this(resourceName, resourceId, null);
    }
}
