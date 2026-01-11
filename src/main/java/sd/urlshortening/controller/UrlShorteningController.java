package sd.urlshortening.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sd.urlshortening.dto.RegisterUrlShorten;
import sd.urlshortening.dto.UrlShorteningResponse;
import sd.urlshortening.entity.UrlShortening;
import sd.urlshortening.service.UrlShorteningService;

import java.net.URI;

@RestController()
public class UrlShorteningController {
    private final UrlShorteningService service;

    UrlShorteningController(UrlShorteningService service) {
        this.service = service;
    }

    @GetMapping("/shorten")
    ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/shorten")
    ResponseEntity<?> newUrl(@Valid @RequestBody RegisterUrlShorten newUrl) {
        UrlShorteningResponse newUrlCreated = service.createUrlShortening(newUrl);
        //TODO use spring HATEOAS to obtain self URL
        return ResponseEntity.created(URI.create("localhost:8080/shorten/" + newUrlCreated.getShortCode()))
                .body(newUrlCreated);
    }

    @GetMapping("/shorten/{shortCode}")
    ResponseEntity<?> getOneByShortCode(@PathVariable String shortCode) {
        return ResponseEntity.ok(service.getOneByShortCode(shortCode));
    }

    @PutMapping("/shorten/{shortCode}")
    ResponseEntity<?> replaceUrlByCode(
            @Valid @RequestBody RegisterUrlShorten updateUrl, @PathVariable String shortCode) {
        return ResponseEntity.ok(service.saveUrl(updateUrl, shortCode));
    }

    @DeleteMapping("/shorten/{shortCode}")
    ResponseEntity<?> deleteByShortCode(@PathVariable String shortCode) {
        service.deleteByShortCode(shortCode);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/shorten/{shortCode}/stats")
    ResponseEntity<?> getStatsByShortenCode(@PathVariable String shortCode) {
        return ResponseEntity.ok(service.getStatsByShortCode(shortCode));
    }
}
