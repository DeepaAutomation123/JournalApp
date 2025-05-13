package rest.engineering.digest.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.engineering.digest.journalApp.entity.UserEntry;
import rest.engineering.digest.journalApp.service.UserEntryService;

import java.util.List;

@RestController
@RequestMapping("/admin")

public class AdminController {
    @Autowired
    UserEntryService userEntryService;
    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers()
    {
        List<UserEntry> all= userEntryService.getAll();
        if(all!=null && all.isEmpty())
        {
            return new  ResponseEntity<>(all, HttpStatus.OK );
        }
        return  new ResponseEntity<>(all,HttpStatus.NOT_FOUND);
    }
    @PostMapping("/create-admin-user")
    public void createUser(@RequestBody UserEntry userEntry)
    {
        userEntryService.saveAdmin(userEntry);
    }
}
