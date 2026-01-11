package sd.urlshortening.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sd.urlshortening.dto.RegisterUrlShorten;
import sd.urlshortening.service.UrlShorteningService;

@Configuration
public class LoadDatabase {
    private static final Logger log =
            LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UrlShorteningService urlShorteningService) {
        return args -> {
            log.info("Preloaded: " + urlShorteningService.createUrlShortening(
                    new RegisterUrlShorten("https://www.google.com.mx")
            ));
        };
    }
}
