package dev.sg.DTOs.user;

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
    private String email;
    private Gender gender;

}
