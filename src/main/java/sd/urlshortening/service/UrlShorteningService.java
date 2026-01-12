package sd.urlshortening.service;

import org.springframework.stereotype.Service;
import sd.urlshortening.dto.RegisterUrlShorten;
import sd.urlshortening.dto.UrlShorteningResponse;
import sd.urlshortening.dto.UrlShorteningWithAccessCountResponse;
import sd.urlshortening.entity.UrlShortening;
import sd.urlshortening.exception.UrlShorteningNotFoundException;
import sd.urlshortening.repository.UrlShorteningRepository;
import sd.urlshortening.utils.Base62Converter;

import java.util.List;

@Service
public class UrlShorteningService {
    private final UrlShorteningRepository repository;

    public UrlShorteningService(UrlShorteningRepository repository) {
        this.repository = repository;
    }

    public List<UrlShorteningResponse> getAll() {
        final List<UrlShortening> allUrls = repository.findAll();
        return allUrls.stream().map(data -> new UrlShorteningResponse(
                data.getId(),
                data.getUrl(),
                data.getCreatedAt(),
                data.getUpdatedAt()
        )).toList();
    }

    public UrlShorteningResponse createUrlShortening(RegisterUrlShorten urlShortening) {
        final UrlShortening newUrlShorten = new UrlShortening();
        newUrlShorten.setUrl(urlShortening.getUrl());
        newUrlShorten.setAccessCount(0L);
        final UrlShortening newUrlShortening = repository.save(newUrlShorten);

        return new UrlShorteningResponse(
                newUrlShortening.getId(),
                newUrlShortening.getUrl(),
                newUrlShortening.getCreatedAt(),
                newUrlShortening.getUpdatedAt()
        );
    }

    public UrlShorteningResponse getOneByShortCode(String shortCode) {
        final Long urlShorteningId = Base62Converter.toDecimal(shortCode);
        final UrlShortening urlShortening = repository.findById(urlShorteningId)
                .orElseThrow(() -> new UrlShorteningNotFoundException(shortCode));
        final int rowsAffected = repository.incrementAccessCount(urlShorteningId);
        if (rowsAffected == 0) {
            throw new UrlShorteningNotFoundException(shortCode);
        }

        return new UrlShorteningResponse(
                urlShortening.getId(),
                urlShortening.getUrl(),
                urlShortening.getCreatedAt(),
                urlShortening.getUpdatedAt()
        );
    }

    public UrlShorteningResponse saveUrl(RegisterUrlShorten updateUrl, String shortCode) {
        final Long urlShorteningId = Base62Converter.toDecimal(shortCode);
        final UrlShortening urlShortening = repository.findById(urlShorteningId)
                .orElseThrow(() -> new UrlShorteningNotFoundException(shortCode));
        urlShortening.setUrl(updateUrl.getUrl());
        urlShortening.setAccessCount(0L);
        final UrlShortening urlShorteningUpdated = repository.save(urlShortening);
        return new UrlShorteningResponse(
                urlShorteningUpdated.getId(),
                urlShorteningUpdated.getUrl(),
                urlShorteningUpdated.getCreatedAt(),
                urlShorteningUpdated.getUpdatedAt()
        );
    }

    public void deleteByShortCode(String shortCode) {
        final Long urlShorteningId = Base62Converter.toDecimal(shortCode);
        final UrlShortening urlShortening = repository.findById(urlShorteningId)
                .orElseThrow(() -> new UrlShorteningNotFoundException(shortCode));
        repository.deleteById(urlShortening.getId());
    }

    public UrlShorteningWithAccessCountResponse getStatsByShortCode(String shortCode) {
        final Long urlShorteningId = Base62Converter.toDecimal(shortCode);
        final UrlShortening urlShortening = repository.findById(urlShorteningId)
                .orElseThrow(() -> new UrlShorteningNotFoundException(shortCode));

        return UrlShorteningWithAccessCountResponse.builder()
                .id(urlShortening.getId())
                .url(urlShortening.getUrl())
                .accessCount(urlShortening.getAccessCount())
                .createdAt(urlShortening.getCreatedAt())
                .updatedAt(urlShortening.getUpdatedAt())
                .build();
    }
}
