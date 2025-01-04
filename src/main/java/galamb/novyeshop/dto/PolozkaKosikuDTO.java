package galamb.novyeshop.dto;

import galamb.novyeshop.dto.ProduktDTO;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class PolozkaKosikuDTO {
    private Long id;
    private ProduktDTO produkt;
    private int mnozstvi;
    private BigDecimal celkovaCena; // Přidáno pro uložení ceny po slevě
    private BigDecimal sleva;
}
