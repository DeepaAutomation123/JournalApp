package rest.engineering.digest.journalApp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rest.engineering.digest.journalApp.entity.UserEntry;
import rest.engineering.digest.journalApp.repository.UserEntryRepo;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserEntryRepo userEntryRepo;

    @ParameterizedTest
    @ValueSource(strings = {
        "ram",
        "shyam",
        "vipul"

    })
    public void testFindByUserName(String name)
    {
        //UserEntry user= userEntryRepo.findByusername("ram");
        //assertTrue(!user.getJournalEntries().isEmpty());
        //assertEquals(4,2+2);
       // assertNotNull(userEntryRepo.findByusername("ram"));
        assertNotNull(userEntryRepo.findByusername(name));
    }
}
