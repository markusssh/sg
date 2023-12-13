package dev.sg.services;

import dev.sg.DTOs.user.ModeratorDTO;
import dev.sg.entities.UserEntity;
import dev.sg.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModeratorUserService {

    private final UserRepo userRepo;

    public ModeratorDTO getDetails(String username) {
        UserEntity user = userRepo.findByUsername(username).orElseThrow();
        return ModeratorDTO.builder()
                .id(user.getId())
                .username(username)
                .name(user.getName())
                .surname(user.getSurname())
                .patronymic(user.getPatronymic())
                .phone(user.getPhone())
                .gender(user.getGender())
                .build();
    }
}
