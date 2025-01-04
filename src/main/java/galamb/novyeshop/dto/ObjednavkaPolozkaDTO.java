package galamb.novyeshop.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ObjednavkaPolozkaDTO {
    private Long id;
    private Long produktId; // ID produktu
    private Long objednavkaId; // ID objednávky
    private int mnozstvi;
    private BigDecimal celkovaCena;
    private BigDecimal sleva;
}
