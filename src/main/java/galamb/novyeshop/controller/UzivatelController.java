package galamb.novyeshop.controller;

import galamb.novyeshop.dto.UzivatelDTO;
import galamb.novyeshop.services.impl.UzivatelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/uzivatele")
public class UzivatelController {

    @Autowired
    private UzivatelService uzivatelService;

    @PostMapping("/registrace")
    public ResponseEntity<UzivatelDTO> registrace(@RequestBody UzivatelDTO uzivatelDTO) {
        UzivatelDTO registraceUzivatel = uzivatelService.registrace(uzivatelDTO);
        return ResponseEntity.ok(registraceUzivatel);
    }

    @PostMapping("/prihlaseni")
    public ResponseEntity<UzivatelDTO> prihlaseni(@RequestBody UzivatelDTO uzivatelDTO) {
        UzivatelDTO uzivatel = uzivatelService.prihlaseni(uzivatelDTO.getEmail(), uzivatelDTO.getHeslo());
        return ResponseEntity.ok(uzivatel);
    }
}
