package galamb.novyeshop.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PaymentController {

    @PostMapping("/api/platby")
    public Map<String, String> createPaymentIntent(@RequestBody Map<String, Object> payload) throws StripeException {
        int amount = (Integer) payload.get("amount"); // částka v centech
        String currency = (String) payload.getOrDefault("currency", "usd");

        // Konfigurace PaymentIntent
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount((long) amount)
                .setCurrency(currency)
                .build();

        // Vytvoření PaymentIntent ve Stripe
        PaymentIntent paymentIntent = PaymentIntent.create(params);

        // Vrácení client_secret zpět na frontend
        Map<String, String> response = new HashMap<>();
        response.put("clientSecret", paymentIntent.getClientSecret());
        return response;
    }
}
