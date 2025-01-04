package galamb.novyeshop.dto;

import galamb.novyeshop.dto.PolozkaKosikuDTO;
import galamb.novyeshop.entity.Uzivatel;
import galamb.novyeshop.entity.emums.StavObjednavky;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
public class ObjednavkaDTO {
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
    private List<PolozkaKosikuDTO> polozky;
    private BigDecimal celkovaCena;
    private BigDecimal sleva;
    private StavObjednavky stav;
    private LocalDateTime datumOdeslani;

    // Getters a Setters
}
