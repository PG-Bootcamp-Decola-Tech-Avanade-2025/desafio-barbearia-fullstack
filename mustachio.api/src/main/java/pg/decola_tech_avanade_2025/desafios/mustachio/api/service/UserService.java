package pg.decola_tech_avanade_2025.desafios.mustachio.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.dto.UserEditorDto;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.dto.UserResponseDto;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.exception_handler.ResourceNotFoundException;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.model.User;
import pg.decola_tech_avanade_2025.desafios.mustachio.api.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public boolean userExistsById(UUID id) {
        return userRepository.existsById(id);
    }

    public UserResponseDto createUser(UserEditorDto editorDto) {
        User newUser = new User();
        BeanUtils.copyProperties(editorDto, newUser);

        userRepository.save(newUser);

        return UserResponseDto.fromUser(newUser);
    }

    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserResponseDto::fromUser).toList();
    }

    public UserResponseDto getUserById(UUID id) throws ResourceNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("users", id));
        return UserResponseDto.fromUser(user);
    }

    public UserResponseDto updateUser(UUID id, UserEditorDto editorDto) throws ResourceNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("users", id));

        BeanUtils.copyProperties(editorDto, user);
        userRepository.save(user);

        return UserResponseDto.fromUser(user);
    }

    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }
}
