package sd.urlshortening.exception;

public class UrlShorteningNotFoundException extends RuntimeException {
    public UrlShorteningNotFoundException(String shortCode) {
        super("Could not find url" + shortCode);
    }
}
