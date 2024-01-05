package dev.sg.services;

import dev.sg.DTOs.user.UserDTO;
import dev.sg.DTOs.user.UserDetailsChangeRequest;
import dev.sg.entities.UserEntity;
import dev.sg.repositories.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

    public UserDTO getDetails(String name) {
        return UserDTO.map(
                userRepo.findByUsername(name).orElseThrow()
        );
    }

    public Optional<UserEntity> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not found", username)
        ));
        return new User(
                user.getUsername(),
                user.getPasswordHashed(),
                user.getRoles().stream().map(
                        role -> new SimpleGrantedAuthority(role.getName())
                ).collect(Collectors.toList())
            );
    }

    public UserDTO changeDetails(String name, UserDetailsChangeRequest userDetailsChangeRequest) {
        UserEntity user = userRepo.findByUsername(name).orElseThrow();
        user.setUsername(userDetailsChangeRequest.getPhone().toString());
        user.setName(userDetailsChangeRequest.getName());
        user.setSurname(userDetailsChangeRequest.getSurname());
        user.setPatronymic(userDetailsChangeRequest.getPatronymic());
        user.setPhone(userDetailsChangeRequest.getPhone());
        user.setBirthdate(userDetailsChangeRequest.getBirthdate());
        user.setGender(userDetailsChangeRequest.getGender());
        return UserDTO.map(userRepo.save(user));
    }
}
