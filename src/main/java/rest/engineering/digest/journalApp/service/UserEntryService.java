package rest.engineering.digest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import rest.engineering.digest.journalApp.entity.JournalEntry;
import rest.engineering.digest.journalApp.entity.UserEntry;
import rest.engineering.digest.journalApp.repository.JournalEntryRepo;
import rest.engineering.digest.journalApp.repository.UserEntryRepo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Component
@Slf4j
public class UserEntryService {
    @Autowired()
    private UserEntryRepo userEntryRepo;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public void saveNewEntry(UserEntry userEntry) {
        try {

            userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
            userEntry.setRoles(Arrays.asList("USER"));
            userEntryRepo.save(userEntry);
        } catch (Exception e) {
            log.error("logger msg");
            log.warn("logger msg");
            log.info("logger msg");
            log.debug("logger msg");
            log.trace("logger msg");
           // log.error("Error occured for {}:", userEntry.getUsername(),e);
        }
    }
    public void saveAdmin(UserEntry userEntry)
    {
        userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
        userEntry.setRoles(Arrays.asList("USER","ADMIN"));
        userEntryRepo.save(userEntry);
    }

    public void saveEntry(UserEntry userEntry)
    {
        userEntryRepo.save(userEntry);
    }

    public List<UserEntry> getAll()
    {
        return userEntryRepo.findAll();
    }

    public Optional<UserEntry> findById(ObjectId id)
    {
        return userEntryRepo.findById(id);
    }
    public void deleteById(ObjectId id)
    {
        userEntryRepo.deleteById(id);
    }
    public UserEntry findByUserName(String username)
    {
        return userEntryRepo.findByusername(username);
    }

}
