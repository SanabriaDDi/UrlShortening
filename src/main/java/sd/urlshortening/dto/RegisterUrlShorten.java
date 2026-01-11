package sd.urlshortening.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterUrlShorten {
    @NotBlank(message = "Url is required")
    @Size(min = 5, message = "Url is too short")
    private String url;
}
