package sd.urlshortening.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sd.urlshortening.entity.UrlShortening;


public interface UrlShorteningRepository extends JpaRepository<UrlShortening, Long> {
}
