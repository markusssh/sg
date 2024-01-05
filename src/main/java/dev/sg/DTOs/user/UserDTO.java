package dev.sg.DTOs.user;

import dev.sg.entities.UserEntity;
import dev.sg.enums.Gender;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserDTO {

    private Long id;
    private String username;
    private String name;
    private String surname;
    private String patronymic;
    private Long phone;
    private LocalDate birthdate;
    private Gender gender;

    public static UserDTO map(UserEntity userEntity) {
        return UserDTO
                .builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .name(userEntity.getName())
                .surname(userEntity.getSurname())
                .patronymic(userEntity.getPatronymic())
                .phone(userEntity.getPhone())
                .birthdate(userEntity.getBirthdate())
                .gender(userEntity.getGender())
                .build();
    }

}
