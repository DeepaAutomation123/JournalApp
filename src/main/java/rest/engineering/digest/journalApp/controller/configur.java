package rest.engineering.digest.journalApp.controller;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class configur {

    @Bean(name = "ujjsl")
    employee getEmpployee(){
        employee employee = new employee(1,"ujjal");
        return employee;
    }

    @Bean(name = "modak")
    employee getEmpployee2(){
        employee employee = new employee(2,"deepa");
        return employee;
    }
}
