package com.example.demo.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.model.Category;
import com.example.demo.repository.categoryRepository;

@Configuration
public class categoryConfig {

    @Bean
    CommandLineRunner commandLineRunner(categoryRepository repository) {
        return args -> {
            repository.saveAll(List.of(
                    new Category("Empty"),
                    new Category("Programming"),
                    new Category("Reading"),
                    new Category("Learning"),
                    new Category("Chilling"),
                    new Category("Watching youtube")
                    
            ));
        };
    }

}
