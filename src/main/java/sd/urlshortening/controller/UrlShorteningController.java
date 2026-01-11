package sd.urlshortening.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sd.urlshortening.entity.UrlShortening;
import sd.urlshortening.repository.UrlShorteningRepository;
import sd.urlshortening.service.UrlShorteningService;

@RestController()
public class UrlShorteningController {
    private UrlShorteningService service;

    UrlShorteningController(UrlShorteningService service) {
        this.service = service;
    }

    @GetMapping("/shorten")
    ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/shorten")
    ResponseEntity<?> newUrl(@RequestBody UrlShortening newUrl) {
        return ResponseEntity.ok(service.createUrlShortening(newUrl));
    }
}
