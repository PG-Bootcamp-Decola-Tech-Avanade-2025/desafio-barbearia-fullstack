package pg.decola_tech_avanade_2025.desafios.mustachio.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.dto.EditorUserDto;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.dto.GetUserDto;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.model.User;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.repository.UserRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody EditorUserDto newUserDto) {
        UUID newUserId = UUID.randomUUID();
        User newUser = new User();
        newUser.setId(newUserId);
        BeanUtils.copyProperties(newUserDto, newUser);
        userRepository.save(newUser);
        return ResponseEntity.created(URI.create(String.format("/users/%s", newUser.getId()))).build();
    }

    @GetMapping
    public ResponseEntity<List<GetUserDto>> findAll() {
        List<User> users = userRepository.findAll();
        List<GetUserDto> userDtos = users.stream().map(user -> {
            GetUserDto getUserDto = new GetUserDto();
            BeanUtils.copyProperties(user, getUserDto);
            return getUserDto;
        }).toList();
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserDto> findById(@PathVariable UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        GetUserDto getUserDto = new GetUserDto();
        BeanUtils.copyProperties(user, getUserDto);
        return ResponseEntity.ok(getUserDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable UUID id, @Valid @RequestBody EditorUserDto newUserDto) {
        Optional<User> newUserOptional = userRepository.findById(id);
        User newUser = newUserOptional.orElseGet(() -> {
            User u = new User();
            u.setId(id);
            return u;
        });
        BeanUtils.copyProperties(newUserDto, newUser);
        userRepository.save(newUser);
        if (newUserOptional.isEmpty()) {
            return ResponseEntity.created(URI.create(String.format("/users/%s", newUser.getId()))).build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }
}
