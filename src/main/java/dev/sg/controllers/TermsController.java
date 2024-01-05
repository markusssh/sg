package dev.sg.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("terms")
public class TermsController {

    @GetMapping("personal_data")
    public String getPDTerms() {
        return "PD_terms";
    }

    @GetMapping("user_agreement")
    public String getUATerms(){
        return "UA_terms";
    }

}
