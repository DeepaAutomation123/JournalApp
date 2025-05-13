package rest.engineering.digest.journalApp.service;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rest.engineering.digest.journalApp.entity.JournalEntry;
import rest.engineering.digest.journalApp.entity.UserEntry;
import rest.engineering.digest.journalApp.repository.JournalEntryRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired()
    private JournalEntryRepo journalEntryRepo;
    @Autowired
    private UserEntryService userService;


    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username) {
        try {
            UserEntry user = userService.findByUserName(username);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepo.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);
        } catch (Exception e) {
            throw new RuntimeException("An error occured while saving the entry", e);
        }
    }

    public void saveEntry(JournalEntry journalEntry) {

        journalEntryRepo.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepo.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String username) {
        boolean removed= false;
        try {
            UserEntry user = userService.findByUserName(username);
             removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveEntry(user);
                journalEntryRepo.deleteById(id);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw  new RuntimeException("An Error Occured while deleting the entry", e);
        }
        return removed;
    }
}
