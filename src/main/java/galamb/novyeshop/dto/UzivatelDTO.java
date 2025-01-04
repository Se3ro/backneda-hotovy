package galamb.novyeshop.dto;

import lombok.Data;

@Data
public class UzivatelDTO {
    private Long id;
    private String jmeno;
    private String prijmeni;
    private String email;
    private String heslo;
}
