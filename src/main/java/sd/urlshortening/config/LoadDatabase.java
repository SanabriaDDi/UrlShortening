package sd.urlshortening.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sd.urlshortening.entity.UrlShortening;
import sd.urlshortening.repository.UrlShorteningRepository;

@Configuration
public class LoadDatabase {
    private static final Logger log =
            LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UrlShorteningRepository urlShorteningRepository) {
        return args -> {
            final UrlShortening urlShortening1 = new UrlShortening("https://www.google.com.mx");
            log.info("Preloaded: " + urlShorteningRepository.save(urlShortening1));
        };
    }
}
