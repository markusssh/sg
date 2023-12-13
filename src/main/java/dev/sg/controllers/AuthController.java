package dev.sg.controllers;

import dev.sg.DTOs.jwt.JwtRequest;
import dev.sg.DTOs.user.RegistrationModeratorDTO;
import dev.sg.DTOs.user.RegistrationUserDTO;
import dev.sg.exeptions.AppError;
import dev.sg.exeptions.IllegalLoginFormatException;
import dev.sg.exeptions.UserAlreadyExistsException;
import dev.sg.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("authenticate")
    public ResponseEntity<?> createAuthToken(
            @RequestBody JwtRequest authRequest
    ) {
        return authService.createAuthToken(authRequest);
    }

    @PostMapping("register")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDTO registrationUserDTO) {
        try {
            authService.createNewUser(registrationUserDTO);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Server error"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.BAD_REQUEST.value(), "User already exists"),
                    HttpStatus.BAD_REQUEST
            );
        } catch (IllegalLoginFormatException e) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.BAD_REQUEST.value(), "Illegal login format"),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @Profile("dev")
    @PostMapping("moder")
    public ResponseEntity<?> createModer(@RequestBody RegistrationModeratorDTO registrationModeratorDTO) {
        try {
            authService.createNewModer(registrationModeratorDTO);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Server error"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.BAD_REQUEST.value(), "User already exists"),
                    HttpStatus.BAD_REQUEST
            );
        } catch (IllegalLoginFormatException e) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.BAD_REQUEST.value(), "Illegal login format"),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

}
