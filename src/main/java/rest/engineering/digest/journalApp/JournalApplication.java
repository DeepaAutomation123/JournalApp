package rest.engineering.digest.journalApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class JournalApplication implements CommandLineRunner {
    public static  void main(String args[])
    {
        SpringApplication.run(JournalApplication.class,args);
    }


    @Qualifier("modak")
    @Autowired
    rest.engineering.digest.journalApp.controller.employee employee;

    @Override
    public void run(String... args) throws Exception {
        System.out.printf(employee.getName());
    }
}
