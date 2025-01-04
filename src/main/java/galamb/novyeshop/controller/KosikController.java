package galamb.novyeshop.controller;

import galamb.novyeshop.dto.PolozkaKosikuDTO;
import galamb.novyeshop.repositoryl.PolozkaKosikuRepozitar;
import galamb.novyeshop.services.KosikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/kosik")
public class KosikController {
    @Autowired
    private KosikService kosikService;
    @Autowired
    private PolozkaKosikuRepozitar polozkaKosikuRepozitar;

    @PostMapping("/pridat")
    public ResponseEntity<Void> addItemToCart(@RequestParam Long productId, @RequestParam int quantity) {
        kosikService.addItemToCart(productId, quantity);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/apply-promo")
    public ResponseEntity<BigDecimal> aplikovatPromoKod(@RequestParam String promoKod) {
        BigDecimal cenaPoSleve = kosikService.aplikovatPromoKod(promoKod);
        return ResponseEntity.ok(cenaPoSleve);
    }

    @GetMapping
    public ResponseEntity<List<PolozkaKosikuDTO>> getAllCartItems() {
        return ResponseEntity.ok(kosikService.getAllCartItems());
    }

    @DeleteMapping("/vymazat")
    public ResponseEntity<Void> vymazatKosik() {
        kosikService.vymazatKosik();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> odstranitPolozku(@PathVariable Long itemId) {
        if (!polozkaKosikuRepozitar.existsById(itemId)) {
            return ResponseEntity.notFound().build();
        }
        polozkaKosikuRepozitar.deleteById(itemId);
        return ResponseEntity.noContent().build();
    }

}

