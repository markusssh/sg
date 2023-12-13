package dev.sg;

import dev.sg.DTOs.user.RegistrationModeratorDTO;
import dev.sg.DTOs.user.RegistrationUserDTO;
import dev.sg.entities.ReportEntity;
import dev.sg.entities.RoleEntity;
import dev.sg.enums.Gender;
import dev.sg.enums.Status;
import dev.sg.repositories.ReportRepo;
import dev.sg.repositories.RoleRepo;
import dev.sg.repositories.UserRepo;
import dev.sg.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class DataInitializer {

    private final AuthService authService;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final ReportRepo reportRepo;

    public void initializeData() {
        initializeRoles();
        generateUsers();
        if (reportRepo.count() == 0) generateReports();

    }

    private void initializeRoles() {
        roleRepo.save(RoleEntity.builder().id(1).name("ROLE_USER").build());
        roleRepo.save(RoleEntity.builder().id(2).name("ROLE_MODERATOR").build());
    }

    private void generateUsers() {
        if (userRepo.findByUsername("9323365668").isEmpty()) {
            authService.createNewUser(
                    RegistrationUserDTO.builder()
                            .username("9323365668")
                            .password("password")
                            .name("Юзер")
                            .surname("Юзеров")
                            .patronymic("Юзерович")
                            .birthdate("2002-03-01")
                            .email("user@email.com")
                            .gender(Gender.M)
                            .build()
            );
        }
        if (userRepo.findByUsername("9999999999").isEmpty()) {
            authService.createNewUser(
                    RegistrationUserDTO.builder()
                            .username("9999999999")
                            .password("password")
                            .name("Юзерина")
                            .surname("Юзерова")
                            .patronymic("Юзеровна")
                            .birthdate("2024-01-01")
                            .email("user1@email.com")
                            .gender(Gender.F)
                            .build()
            );
        }
        if (userRepo.findByUsername("8888888888").isEmpty()) {
            authService.createNewUser(
                    RegistrationUserDTO.builder()
                            .username("8888888888")
                            .password("password")
                            .name("ААА")
                            .surname("АААов")
                            .patronymic("АААвич")
                            .birthdate("2077-04-13")
                            .email("user2@email.com")
                            .gender(Gender.M)
                            .build()
            );
        }

        if (userRepo.findByUsername("moder").isEmpty()) {
            authService.createNewModer(
                    RegistrationModeratorDTO.builder()
                            .username("moder")
                            .password("password")
                            .name("Модер")
                            .surname("Модеров")
                            .patronymic("Модерович")
                            .phone(9323365668L)
                            .gender(Gender.M)
                            .build()
            );
        }
    }

    private void generateReports() {
        for (int i = 0; i < 10000; i++) {
            ReportEntity reportEntity = new ReportEntity();
            reportEntity.setLinks(null);
            reportEntity.setAddress("address");
            reportEntity.setGeotag("geotag");
            Random random = new Random();

            int randUser = random.nextInt(3);
            var user = switch(randUser){
                case 0 -> userRepo.findByUsername("9323365668").get();
                case 1 -> userRepo.findByUsername("9999999999").get();
                case 2 -> userRepo.findByUsername("8888888888").get();
                default -> throw new IllegalStateException("Unexpected value: " + randUser);
            };
            reportEntity.setUser(user);

            String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < 10; j++) {
                char randomChar = characters.charAt(random.nextInt(characters.length()));
                stringBuilder.append(randomChar);
            }
            reportEntity.setBody(stringBuilder.toString());

            reportEntity.setIsStatusChanged(random.nextBoolean());

            int year = 2022 + random.nextInt(LocalDateTime.now().getYear() - 2022 + 1);
            int month = 1 + random.nextInt(12);
            int day = 1 + random.nextInt(LocalDateTime.of(year, month, 1, 0, 0).toLocalDate().lengthOfMonth());
            int hour = random.nextInt(24);
            int minute = random.nextInt(60);
            int second = random.nextInt(60);
            reportEntity.setCreatedAt(LocalDateTime.of(year, month, day, hour, minute, second));

            int randStatus = random.nextInt(4);
            var status = switch(randStatus){
                case 0 -> Status.NEW;
                case 1 -> Status.PENDING;
                case 2 -> Status.DENIED;
                case 3 -> Status.APPROVED;
                default -> throw new IllegalStateException("Unexpected value: " + randUser);
            };
            reportEntity.setStatus(status);

            reportEntity.setCategoryId(random.nextInt(342));

            reportRepo.save(reportEntity);
        }
    }
}
