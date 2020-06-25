package com.example.proiectjava;

import com.example.proiectjava.dao.VacantaDAO;
import com.example.proiectjava.model.Vacanta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootApplication
public class AplicatieVacantaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AplicatieVacantaApplication.class, args);

    }

}
