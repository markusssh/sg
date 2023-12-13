package dev.sg.controllers.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("user/details")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("name")
    public String getUsername(Principal principal) {
        return principal.getName();
    }

}
