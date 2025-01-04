package galamb.novyeshop.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Entity
public class PolozkaKosiku {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Produkt produkt;
    @ManyToOne
    private Objednavka objednavka;
    private int mnozstvi;
    private BigDecimal celkovaCena; // Cena po aplikaci slevy
    private BigDecimal sleva; // Sleva aplikovaná na tuto položku
    private boolean promoKodAplikovan;
    private String promoKod;


}
