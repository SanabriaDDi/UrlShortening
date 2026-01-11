package sd.urlshortening.service;

import org.springframework.stereotype.Service;
import sd.urlshortening.dto.UrlShorteningResponse;
import sd.urlshortening.entity.UrlShortening;
import sd.urlshortening.repository.UrlShorteningRepository;
import sd.urlshortening.utils.Base62Converter;

import java.util.List;

@Service
public class UrlShorteningService {
    private UrlShorteningRepository repository;

    public UrlShorteningService(UrlShorteningRepository repository) {
        this.repository = repository;
    }

    public List<UrlShorteningResponse> getAll() {
        final List<UrlShortening> allUrls = repository.findAll();
        return allUrls.stream().map(data -> new UrlShorteningResponse(
                data.getId(),
                data.getUrl(),
                Base62Converter.fromDecimal(data.getId()),
                data.getCreatedAt(),
                data.getUpdatedAt()
        )).toList();
    }

    public UrlShorteningResponse createUrlShortening(UrlShortening urlShortening) {
        final UrlShortening newUrlShortening = repository.save(urlShortening);

        return new UrlShorteningResponse(
                newUrlShortening.getId(),
                newUrlShortening.getUrl(),
                Base62Converter.fromDecimal(newUrlShortening.getId()),
                newUrlShortening.getCreatedAt(),
                newUrlShortening.getUpdatedAt()
        );
    }
}
