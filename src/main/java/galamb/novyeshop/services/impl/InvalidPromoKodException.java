package galamb.novyeshop.services.impl;

public class InvalidPromoKodException extends RuntimeException {
    // Konstruktor pro výjimku s vlastní chybovou zprávou
    public InvalidPromoKodException(String message) {
        super(message);
    }
}
