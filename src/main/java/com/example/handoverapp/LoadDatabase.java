package com.example.handoverapp;

import com.example.handoverapp.entity.Task;
import com.example.handoverapp.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(TaskRepository repository) {
        Task completedTask = new Task("Example description 3","Example outcome 3","escalation plan","location","diagnosis","creator");
        completedTask.setCompleted(true);
        return args -> {
            log.info("Preloading "+repository.save(new Task("Example description 1","Example outcome 1","escalation plan","location","diagnosis","creator")));
            log.info("Preloading "+repository.save(new Task("Example description 2","Example outcome 2","escalation plan","location","diagnosis","creator")));
            log.info("Preloading "+repository.save(completedTask));
        };
    }
}