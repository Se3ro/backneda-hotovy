package galamb.novyeshop.entity;

import galamb.novyeshop.entity.emums.Kategorie;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
public class Produkt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nazev;
    private String popis;
    private BigDecimal cena;
    private String slozeni;
    @Enumerated(EnumType.STRING)
    private Kategorie kategorie;
    private String obrazek;

}