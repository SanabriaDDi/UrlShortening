package sd.urlshortening.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import sd.urlshortening.entity.UrlShortening;


public interface UrlShorteningRepository extends JpaRepository<UrlShortening, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE UrlShortening SET accessCount = COALESCE(accessCount, 0) + 1 WHERE id = :id")
    int incrementAccessCount(@Param("id") Long id);
}
