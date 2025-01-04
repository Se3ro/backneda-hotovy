package galamb.novyeshop.controller;

import galamb.novyeshop.dto.ObjednavkaDTO;
import galamb.novyeshop.entity.emums.StavObjednavky;
import galamb.novyeshop.services.ObjednavkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping( "/api/objednavky")
public class ObjednavkaController {

    @Autowired
    private ObjednavkaService objednavkaService;

    @PostMapping
    public ResponseEntity<ObjednavkaDTO> vytvoritObjednavku(@RequestBody ObjednavkaDTO objednavkaDTO) {
        ObjednavkaDTO vytvorenaObjednavka = objednavkaService.vytvoritObjednavku(objednavkaDTO);
        return ResponseEntity.ok(vytvorenaObjednavka);
    }

    @GetMapping
    public ResponseEntity<List<ObjednavkaDTO>> getObjednavky() {
        List<ObjednavkaDTO> objednavky = objednavkaService.getObjednavky();
        return ResponseEntity.ok(objednavky);
    }

}
