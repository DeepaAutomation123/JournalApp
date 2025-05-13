package rest.engineering.digest.journalApp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import rest.engineering.digest.journalApp.entity.UserEntry;
import rest.engineering.digest.journalApp.repository.UserEntryRepo;

import java.util.ArrayList;

import static  org.mockito.Mockito.*;
import static org.springframework.security.core.userdetails.User.*;

@ActiveProfiles("dev")
public class UserDetailServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;
    @Mock
    private UserEntryRepo userEntryRepo;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }
    @Test
     @Disabled
    /*void loadUserByUsernameTest()
    {
       when(userEntryRepo.findByusername(ArgumentMatchers.anyString())).thenReturn(User.builder()
                .username("ram").password("hkkhkhk").roles(new ArrayList<>()).build());
        UserDetails user =userDetailsService.loadUserByUsername("ram");
        Assertions.assertNotNull(user);

    }*/

    void loadUserByUsernameTest() {
        // Create a mock UserEntry (your own entity class)
        UserEntry mockUser = new UserEntry();
        mockUser.setUsername("ram");
        mockUser.setPassword("hkkhkhk");
        mockUser.setRoles(new ArrayList<>()); // or Arrays.asList("USER")

        // Mock the repo to return your UserEntry
        when(userEntryRepo.findByusername(ArgumentMatchers.anyString()))
                .thenReturn(mockUser);

        // Call the service
        UserDetails user = userDetailsService.loadUserByUsername("ram");

        // Assert result
        Assertions.assertNotNull(user);
       // Assertions.assertEquals("ram", user.getUsername());
    }
}
