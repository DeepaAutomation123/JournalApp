package rest.engineering.digest.journalApp.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import rest.engineering.digest.journalApp.entity.JournalEntry;
import rest.engineering.digest.journalApp.entity.UserEntry;
import rest.engineering.digest.journalApp.repository.UserEntryRepo;
import rest.engineering.digest.journalApp.service.JournalEntryService;
import rest.engineering.digest.journalApp.service.UserEntryService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/user")

public class UserEntryController {
    @Autowired
    private UserEntryService userEntryService;
    @Autowired
    private UserEntryRepo userEntryRepo;

    @GetMapping("/health-check")
    public String healthCheck()
    {
        return "Ok1";
    }

    @PutMapping()
    public ResponseEntity<?> UpdateEntry(@RequestBody UserEntry user)
    {
        System.out.println("ok2");
       Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
       String userName=authentication.getName();
       UserEntry userInDb = userEntryService.findByUserName(userName);
           userInDb.setUsername(user.getUsername());
           userInDb.setPassword(user.getPassword());
           userEntryService.saveNewEntry(userInDb);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
  
}
