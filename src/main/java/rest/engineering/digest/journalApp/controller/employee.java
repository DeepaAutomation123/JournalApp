package rest.engineering.digest.journalApp.controller;


import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


public class employee {
    Integer id;
    String name;
    public Integer getId() {
        return id;
    }

    public employee(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
