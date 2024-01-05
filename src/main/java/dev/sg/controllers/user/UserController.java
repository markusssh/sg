package dev.sg.controllers.user;

import dev.sg.DTOs.user.ChangePasswordRequest;
import dev.sg.DTOs.user.UserDetailsChangeRequest;
import dev.sg.exeptions.AppError;
import dev.sg.services.AuthService;
import dev.sg.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("user/details")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @GetMapping
    public ResponseEntity<?> getDetails(Principal principal) {
        try {
            return ResponseEntity.ok(userService.getDetails(principal.getName()));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.UNAUTHORIZED.value(), "Credentials have changed"),
                    HttpStatus.UNAUTHORIZED
            );
        }
    }

    @PatchMapping
    public ResponseEntity<?> changeDetails(
            Principal principal,
            @RequestBody UserDetailsChangeRequest userDetailsChangeRequest,
            HttpServletRequest httpServletRequest
    ) {
        try {
            if (!userDetailsChangeRequest.getPhone().toString().equals(principal.getName())) {
                authService.killToken(httpServletRequest.getHeader("Authorization").substring(7));
            }
            return ResponseEntity.ok(userService.changeDetails(principal.getName(), userDetailsChangeRequest));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.UNAUTHORIZED.value(), "Credentials have changed"),
                    HttpStatus.UNAUTHORIZED
            );
        }
    }

    @PatchMapping("password")
    public ResponseEntity<?> changePassword(
            Principal principal,
            @RequestBody ChangePasswordRequest changePasswordRequest,
            HttpServletRequest httpServletRequest
    ) {
        try {
            authService.changePassword(
                    principal.getName(),
                    changePasswordRequest,
                    httpServletRequest.getHeader("Authorization").substring(7)
            );
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.UNAUTHORIZED.value(), "Credentials have changed"),
                    HttpStatus.UNAUTHORIZED
            );
        }
    }

}
