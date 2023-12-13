package dev.sg.DTOs.user;

import dev.sg.enums.Gender;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationUserDTO {

    private String username;
    private String password;
    private String name;
    private String surname;
    private String patronymic;

    /**
     * Format is ISO-8601 calendar system: 2002-12-31
     */
    private String birthdate;

    private String email;
    private Gender gender;


}
