package galamb.novyeshop.controller;

import galamb.novyeshop.dto.SberacKontaktuDTO;
import galamb.novyeshop.services.SberacKontaktuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/kontakty")
public class SberacKontaktuController {

    @Autowired
    private SberacKontaktuService sberacKontaktuService;

    @PostMapping
    public ResponseEntity<SberacKontaktuDTO> ulozitKontakt(@RequestBody SberacKontaktuDTO sberacKontaktuDTO) {
        System.out.println("Přijaté DTO: " + sberacKontaktuDTO); // Ladicí výpis
        if (sberacKontaktuDTO.getEmail() == null || sberacKontaktuDTO.getTelefon() == null) {
            return ResponseEntity.badRequest().build();
        }
        LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);
        sberacKontaktuDTO.setDatumOdeslani(now);

        SberacKontaktuDTO ulozenyKontakt = sberacKontaktuService.saveKontakt(sberacKontaktuDTO);
        System.out.println("Uložený kontakt: " + ulozenyKontakt); // Ladicí výpis
        return ResponseEntity.ok(ulozenyKontakt);
    }


    @GetMapping
    public ResponseEntity<List<SberacKontaktuDTO>> ziskatVsechnyKontakty() {
        List<SberacKontaktuDTO> kontakty = sberacKontaktuService.getAllSberaciKontaktu();
        return ResponseEntity.ok(kontakty);
    }
}
