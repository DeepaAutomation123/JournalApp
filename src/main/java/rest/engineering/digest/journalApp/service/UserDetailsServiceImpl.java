package rest.engineering.digest.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import rest.engineering.digest.journalApp.entity.UserEntry;
import rest.engineering.digest.journalApp.repository.UserEntryRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserEntryRepo userEntryRepo;
  /*  @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      UserEntry user= userEntryRepo.findByusername(username);
      if(user!=null)
      {
          return org.springframework.security.core.userdetails.User.builder()
                  .username(user.getUsername())
                  .username(user.getPassword())
                  .roles(user.getRoles().toArray(new String[0]))
                  .build();

      }
        throw new UsernameNotFoundException("user not found with username: "+username);
    }*/
  /* @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       UserEntry user = userEntryRepo.findByusername(username);
       if (user == null) {
           throw new UsernameNotFoundException("User not found with username: " + username);
       }
       // Ensure BCrypt is used for password matching
       return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());

   }*/
   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       UserEntry user = userEntryRepo.findByusername(username);// Fetch user from MongoDB

       // Map the roles to authorities
       List<GrantedAuthority> authorities = user.getRoles().stream()
               .map(role -> new SimpleGrantedAuthority(role)) // Convert roles to authorities
               .collect(Collectors.toList());

       return new User(user.getUsername(), user.getPassword(), authorities);
   }

}
