package dev.sg;

import dev.sg.database_initialization_dev.DataInitializer;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class SgApplication {

    private final DataInitializer dataInitializer;

    public static void main(String[] args) {
        SpringApplication.run(SgApplication.class, args);
    }

    @PostConstruct
    public void initializeDataOnStartup() {
        dataInitializer.initialize();
    }
}
