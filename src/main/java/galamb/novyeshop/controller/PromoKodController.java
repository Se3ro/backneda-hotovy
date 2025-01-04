package galamb.novyeshop.controller;

import galamb.novyeshop.dto.PromoKodDTO;
import galamb.novyeshop.entity.PromoKod;
import galamb.novyeshop.services.KosikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/promokody")
public class PromoKodController {
    @Autowired
    private KosikService kosikService; // Změněno na kosikService

    @PostMapping
    public PromoKod pridatPromoKod(@RequestBody PromoKodDTO promoKodDTO) {
        return kosikService.pridatPromoKod(promoKodDTO); // Změněno na kosikService
    }

    @PutMapping("/{id}")
    public PromoKod aktualizovatPromoKod(@PathVariable Long id, @RequestBody PromoKodDTO promoKodDTO) {
        return kosikService.aktualizovatPromoKod(id, promoKodDTO); // Změněno na kosikService
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> smazatPromoKod(@PathVariable Long id) {
        kosikService.smazatPromoKod(id); // Změněno na kosikService
        return ResponseEntity.ok().build();
    }
}
