package galamb.novyeshop.entity;

import galamb.novyeshop.entity.ObjednavkaPolozka;
import galamb.novyeshop.entity.emums.StavObjednavky;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Objednavka {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String jmeno;
    private String prijmeni;
    private String email;
    private String telefon;
    private String ulice;
    private String mesto;
    private String zeme;
    private String psc;
    private String zpusobDopravy;
    private String zpusobPlatby;
    @OneToMany(mappedBy = "objednavka", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ObjednavkaPolozka> polozky;
    private BigDecimal celkovaCena; // Cena po aplikaci všech slev
    private String promoKod;
    private BigDecimal sleva; // Celková sleva na objednávku
    @Enumerated(EnumType.STRING)
    private StavObjednavky stav;
    private LocalDateTime datumOdeslani;
}