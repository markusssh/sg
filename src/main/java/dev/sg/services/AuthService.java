package dev.sg.services;

import dev.sg.DTOs.jwt.JwtRequest;
import dev.sg.DTOs.jwt.JwtResponse;
import dev.sg.DTOs.user.ChangePasswordRequest;
import dev.sg.DTOs.user.RegistrationModeratorDTO;
import dev.sg.DTOs.user.RegistrationUserDTO;
import dev.sg.entities.BadTokenEntity;
import dev.sg.entities.UserEntity;
import dev.sg.exeptions.AppError;
import dev.sg.exeptions.IllegalLoginFormatException;
import dev.sg.exeptions.UserAlreadyExistsException;
import dev.sg.repositories.BadTokenRepo;
import dev.sg.repositories.RoleRepo;
import dev.sg.repositories.UserRepo;
import dev.sg.utils.JwtTokenUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;
    private final RoleRepo roleRepo;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final BadTokenRepo badTokenRepo;

    public ResponseEntity<?> createAuthToken(
            @RequestBody JwtRequest authRequest
    ) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.UNAUTHORIZED.value(), "Wrong login or password"),
                    HttpStatus.UNAUTHORIZED
            );
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    public void createNewUser(RegistrationUserDTO registrationUserDTO) {
        if (userRepo.findByUsername(registrationUserDTO.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException(String.format("User '%s' does already exist",
                    registrationUserDTO.getUsername()));
        } else if (roleRepo.findByName("ROLE_USER").isEmpty()) {
            throw new NoSuchElementException("Table roles doesn't contain ROLE_USER");
        } else if (!isLoginValid(registrationUserDTO.getUsername())) {
            throw new IllegalLoginFormatException("Illegal login format");
        } else {
            userRepo.save(
                    UserEntity
                            .builder()
                            .username(registrationUserDTO.getUsername())
                            .passwordHashed(passwordEncoder.encode(registrationUserDTO.getPassword()))
                            .name(registrationUserDTO.getName())
                            .surname(registrationUserDTO.getSurname())
                            .patronymic(registrationUserDTO.getPatronymic())
                            .birthdate(
                                    (registrationUserDTO.getBirthdate() == null) ?
                                            null
                                            : LocalDate.parse(registrationUserDTO.getBirthdate())
                            )
                            .phone(Long.valueOf(registrationUserDTO.getUsername()))
                            .gender(registrationUserDTO.getGender())
                            .roles(List.of(roleRepo.findByName("ROLE_USER").get()))
                            .build()
            );
        }
    }

    public void createNewModer(RegistrationModeratorDTO registrationModeratorDTO) {
        if (userRepo.findByUsername(registrationModeratorDTO.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException(String.format("user '%s' does already exist",
                    registrationModeratorDTO.getUsername()));
        } else if (roleRepo.findByName("ROLE_MODERATOR").isEmpty()) {
            throw new NoSuchElementException("table roles doesn't contain ROLE_USER");
        } else {
            userRepo.save(
                    UserEntity
                            .builder()
                            .username(registrationModeratorDTO.getUsername())
                            .passwordHashed(passwordEncoder.encode(registrationModeratorDTO.getPassword()))
                            .name(registrationModeratorDTO.getName())
                            .surname(registrationModeratorDTO.getSurname())
                            .patronymic(registrationModeratorDTO.getPatronymic())
                            .birthdate(null)
                            .phone(registrationModeratorDTO.getPhone())
                            .gender(registrationModeratorDTO.getGender())
                            .roles(List.of(roleRepo.findByName("ROLE_MODERATOR").get()))
                            .build()
            );
        }
    }

    public boolean isLoginValid(String username) {
        String regx = "^[^0]\\d{9}$";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(username);
        return (matcher.matches());
    }

    @Transactional
    public void changePassword(String name, ChangePasswordRequest changePasswordRequest, String jwt) {
        UserEntity user = userRepo.findByUsername(name).orElseThrow();
        user.setPasswordHashed(passwordEncoder.encode(changePasswordRequest.getPassword()));
        killToken(jwt);
        userRepo.save(user);
    }

    public void killToken(String jwt) {
        badTokenRepo.save(
                BadTokenEntity.builder()
                        .token(jwt)
                        .build()
        );
    }
}
