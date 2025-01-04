package galamb.novyeshop.services;

import galamb.novyeshop.dto.PolozkaKosikuDTO;
import galamb.novyeshop.dto.PromoKodDTO;
import galamb.novyeshop.entity.PromoKod;

import java.math.BigDecimal;
import java.util.List;

public interface KosikService {
    void addItemToCart(Long productId, int quantity);
    BigDecimal aplikovatPromoKod(String promoKod);
    List<PolozkaKosikuDTO> getAllCartItems();
    void vymazatKosik(); // Nová metoda pro vymazání košíku

    // Metody pro správu promokódů
    PromoKod pridatPromoKod(PromoKodDTO promoKodDTO);
    PromoKod aktualizovatPromoKod(Long id, PromoKodDTO promoKodDTO);
    void smazatPromoKod(Long id);
}
