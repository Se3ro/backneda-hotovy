package galamb.novyeshop.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
public class MnozstevniSleva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int mnozstvi; // Počet kusů, při kterém se sleva aplikuje
    private BigDecimal sleva; // Sleva v procentech (např. 0.10 pro 10%)
}
