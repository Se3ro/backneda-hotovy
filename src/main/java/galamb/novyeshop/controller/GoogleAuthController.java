package galamb.novyeshop.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;

import galamb.novyeshop.dto.UzivatelDTO;
import galamb.novyeshop.services.impl.UzivatelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/uzivatele")
public class GoogleAuthController {

    private static final Logger logger = LoggerFactory.getLogger(GoogleAuthController.class);

    @Autowired
    private UzivatelService uzivatelService;

    private static final String CLIENT_ID = "77777548051-rtoa9vkag6k8sh1oash25haebjpj7to9.apps.googleusercontent.com";

    @PostMapping("/google-prihlaseni")
    public ResponseEntity<?> googlePrihlaseni(@RequestBody String token) {
        try {
            logger.info("Received token: {}", token);  // Log token
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
                    .setAudience(Collections.singletonList(CLIENT_ID))
                    .build();

            // Ověření tokenu
            GoogleIdToken idToken = verifier.verify(token);
            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();
                logger.info("Token valid, Payload: {}", payload);  // Log payload

                String email = payload.getEmail();
                String firstName = (String) payload.get("given_name");
                String lastName = (String) payload.get("family_name");

                UzivatelDTO uzivatel = uzivatelService.registraceNeboPrihlaseniPresGoogle(email, firstName, lastName);
                return ResponseEntity.ok(uzivatel);
            } else {
                logger.warn("Token nebyl ověřen.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token nebyl ověřen.");
            }
        } catch (Exception e) {
            logger.error("Chyba při zpracování Google tokenu: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Chyba při zpracování Google tokenu.");
        }
    }
}
