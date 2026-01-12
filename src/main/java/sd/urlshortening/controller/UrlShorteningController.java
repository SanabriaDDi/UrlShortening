package sd.urlshortening.controller;

import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sd.urlshortening.dto.RegisterUrlShorten;
import sd.urlshortening.dto.UrlShorteningResponse;
import sd.urlshortening.hateoas.UrlShortingModelAssembler;
import sd.urlshortening.service.UrlShorteningService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController()
public class UrlShorteningController {
    private final UrlShorteningService service;
    private final UrlShortingModelAssembler assembler;

    UrlShorteningController(UrlShorteningService service, UrlShortingModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping("/shorten")
    ResponseEntity<CollectionModel<EntityModel<UrlShorteningResponse>>> getAll() {
        List<EntityModel<UrlShorteningResponse>> urlShorteningResponses = service.getAll()
                .stream()
                .map(assembler::toModel)
                .toList();
        return ResponseEntity.ok(CollectionModel.of(
                urlShorteningResponses,
                linkTo(methodOn(UrlShorteningController.class).getAll()).withSelfRel()
        ));
    }

    @PostMapping("/shorten")
    ResponseEntity<EntityModel<UrlShorteningResponse>> newUrl(@Valid @RequestBody RegisterUrlShorten newUrl) {
        EntityModel<UrlShorteningResponse> entityModel = assembler.toModel(service.createUrlShortening(newUrl));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/shorten/{shortCode}")
    public ResponseEntity<EntityModel<UrlShorteningResponse>> getOneByShortCode(@PathVariable String shortCode) {
        return ResponseEntity.ok(assembler.toModel(service.getOneByShortCode(shortCode)));
    }

    @PutMapping("/shorten/{shortCode}")
    ResponseEntity<EntityModel<UrlShorteningResponse>> replaceUrlByCode(
            @Valid @RequestBody RegisterUrlShorten updateUrl, @PathVariable String shortCode) {
        return ResponseEntity.ok(assembler.toModel(service.saveUrl(updateUrl, shortCode)));
    }

    @DeleteMapping("/shorten/{shortCode}")
    ResponseEntity<?> deleteByShortCode(@PathVariable String shortCode) {
        service.deleteByShortCode(shortCode);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/shorten/{shortCode}/stats")
    ResponseEntity<EntityModel<UrlShorteningResponse>> getStatsByShortenCode(@PathVariable String shortCode) {
        return ResponseEntity.ok(assembler.toModel(service.getStatsByShortCode(shortCode)));
    }
}
