package galamb.novyeshop.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;



@Data
@Entity// Lombok generuje gettery, settery, toString, hashCode, equals a konstruktor
public class SberacKontaktu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;  // E-mailová adresa uživatele
    private String telefon;  // Telefonní číslo uživatele
    private String jmeno;
    private LocalDateTime datumOdeslani;  // Datum a čas odeslání (např. odeslání objednávky nebo registrace)

    @PrePersist
    public void predUlozenim() {
        if (datumOdeslani == null) {
            datumOdeslani = LocalDateTime.now(); // Nastavení aktuálního času, pokud není datum zadáno
        }
    }

}
