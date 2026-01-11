package sd.urlshortening.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import sd.urlshortening.utils.Base62Converter;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UrlShorteningResponse {
    private Long id;
    private String url;
    private String shortCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UrlShorteningResponse(Long id, String url, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.url = url;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getShortCode() {
        return Base62Converter.fromDecimal(id);
    }
}
