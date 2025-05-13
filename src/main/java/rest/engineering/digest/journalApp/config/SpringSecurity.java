package rest.engineering.digest.journalApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.authentication.*;
import rest.engineering.digest.journalApp.service.UserDetailsServiceImpl;
import rest.engineering.digest.journalApp.service.UserEntryService;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    private final UserDetailsServiceImpl userDetailsService;

    public SpringSecurity(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    // Spring Security filter chain configuration
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/journal/**", "/user/**").authenticated() // Protect '/journal/**' path
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .anyRequest().permitAll() // Allow all other requests
                )
                .httpBasic(withDefaults()) // Enable HTTP Basic authentication
                .csrf(csrf -> csrf.disable()); // Disable CSRF protection for stateless APIs

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Creating BCryptPasswordEncoder bean
    }

    // AuthenticationManager bean method, configuring with UserDetailsService and PasswordEncoder
   /* @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration, PasswordEncoder passwordEncoder) throws Exception {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService); // Inject UserDetailsService
        authenticationProvider.setPasswordEncoder(passwordEncoder); // Inject PasswordEncoder


        // Return the AuthenticationManager from AuthenticationConfiguration
        return authenticationConfiguration.getAuthenticationManager();
    }*/

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        // We don't need to use `.and()` here anymore. Use `AuthenticationManagerBuilder` directly from `http`.
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(userDetailsService) // Set your custom UserDetailsService
                .passwordEncoder(passwordEncoder); // Set your custom PasswordEncoder

        return authenticationManagerBuilder.build(); // Build and return the AuthenticationManager
    }

}
