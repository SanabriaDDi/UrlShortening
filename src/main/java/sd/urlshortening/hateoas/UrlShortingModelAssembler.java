package sd.urlshortening.hateoas;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import sd.urlshortening.controller.UrlShorteningController;
import sd.urlshortening.dto.UrlShorteningResponse;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UrlShortingModelAssembler
        implements RepresentationModelAssembler<UrlShorteningResponse, EntityModel<UrlShorteningResponse>> {
    @Override
    public EntityModel<UrlShorteningResponse> toModel(UrlShorteningResponse entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(UrlShorteningController.class).getOneByShortCode(entity.getShortCode())).withSelfRel()
        );
    }
}
