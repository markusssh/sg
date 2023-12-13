package dev.sg.controllers.moderator;

import dev.sg.exeptions.AppError;
import dev.sg.services.ModeratorUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("moderator/details")
@RequiredArgsConstructor
public class ModeratorUserController {

    private final ModeratorUserService moderatorUserService;

    @GetMapping
    public ResponseEntity<?> getDetails(Principal principal) {
        try {
            return ResponseEntity.ok(moderatorUserService.getDetails(principal.getName()));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Server error"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

    }

}
