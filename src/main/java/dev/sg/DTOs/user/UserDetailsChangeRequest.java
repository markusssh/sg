package dev.sg.DTOs.user;

import dev.sg.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDetailsChangeRequest {

    private String name;
    private String surname;
    private String patronymic;
    private Long phone;
    private LocalDate birthdate;
    private Gender gender;

}
