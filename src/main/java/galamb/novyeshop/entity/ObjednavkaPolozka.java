package galamb.novyeshop.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
public class ObjednavkaPolozka {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Produkt produkt;

    @ManyToOne
    private Objednavka objednavka;

    private int mnozstvi;
    private BigDecimal celkovaCena;
    private BigDecimal sleva;
}
