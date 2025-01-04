package galamb.novyeshop.dto;

import galamb.novyeshop.entity.Produkt;
import galamb.novyeshop.entity.emums.Kategorie;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data

public class ProduktDTO {
    private Long id;
    private String nazev;
    private String popis;
    private BigDecimal cena;
    private String slozeni;
    private Kategorie kategorie;
    private String obrazek;




}
