package galamb.novyeshop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class SberacKontaktuDTO {
    private Long id;
    private String jmeno;
    @NotNull(message = "Email nesmí být prázdný")
    @Email(message = "Neplatný formát emailu")
    private String email;

    @NotNull(message = "Telefon nesmí být prázdný")
    private String telefon;

    private LocalDateTime datumOdeslani;
}
