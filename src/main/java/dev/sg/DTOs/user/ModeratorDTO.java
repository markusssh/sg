package dev.sg.DTOs.user;

import dev.sg.enums.Gender;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ModeratorDTO {

    private Long id;
    private String username;
    private String name;
    private String surname;
    private String patronymic;
    private Long phone;
    private Gender gender;

}
