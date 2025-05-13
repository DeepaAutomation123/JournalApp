package rest.engineering.digest.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import rest.engineering.digest.journalApp.entity.UserEntry;
import rest.engineering.digest.journalApp.service.UserEntryService;


@RestController
@RequestMapping("/public")

public class PublicController {

    @Autowired
    private UserEntryService userEntryService;
    @GetMapping("/health-check")
    public String healthCheck()
    {
        return "Ok";
    }

    @PostMapping("create-user")
    public UserEntry createEntry(@RequestBody UserEntry user)
    {
        userEntryService.saveNewEntry(user);
        return user;
    }
}
