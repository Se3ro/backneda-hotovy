package galamb.novyeshop.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class PromoKodDTO {
    private Long id;
    private String kod;
    private BigDecimal sleva;
    private boolean aktivni;
    // Getters and Setters
}
