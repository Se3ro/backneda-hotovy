package galamb.novyeshop.services;


import galamb.novyeshop.dto.SberacKontaktuDTO;

import java.util.List;

public interface SberacKontaktuService {
    SberacKontaktuDTO saveKontakt(SberacKontaktuDTO sberacKontaktuDTO);
    List<SberacKontaktuDTO> getAllSberaciKontaktu();
}