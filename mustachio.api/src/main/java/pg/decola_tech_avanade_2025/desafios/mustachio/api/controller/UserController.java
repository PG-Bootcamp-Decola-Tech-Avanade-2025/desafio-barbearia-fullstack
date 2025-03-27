package pg.decola_tech_avanade_2025.desafios.mustachio.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.dto.UserEditorDto;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.dto.UserResponseDto;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.exception_handler.ResourceNotFoundException;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.service.UserService;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody UserEditorDto editorDto) {
        UserResponseDto responseDto = userService.createUser(editorDto);
        return ResponseEntity.created(
            URI.create("/users/" + responseDto.getId())
        ).build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll() {
        List<UserResponseDto> responseDtos = userService.getAllUsers();
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable UUID id) throws ResourceNotFoundException {
        UserResponseDto responseDto = userService.getUserById(id);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> createOrUpdate(@PathVariable UUID id, @Valid @RequestBody UserEditorDto editorDto) throws ResourceNotFoundException {
        if (userService.userExistsById(id)) {
            userService.updateUser(id, editorDto);
            return ResponseEntity.ok().build();
        } else {
            UserResponseDto responseDto = userService.createUser(editorDto);
            return ResponseEntity.created(
                URI.create("/users/" + responseDto.getId())
            ).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }
}
