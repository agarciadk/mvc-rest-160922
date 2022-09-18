package es.bootcamp.mvcrest.repository;

import es.bootcamp.mvcrest.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {
        return args -> {
            repository.save(new User("Rand", "al'Thor"));
            repository.save(new User("Perrin", "Aybara"));
            repository.save(new User("Nynaeve", "al'Meara"));
            repository.save(new User("Mat", "Cauthon"));
            repository.save(new User("Egwene", "al'Vere"));

            repository.findAll().forEach(user -> log.info("Preloaded " + user));
        };
    }
}
