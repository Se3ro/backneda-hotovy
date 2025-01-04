package galamb.novyeshop.controller;

import galamb.novyeshop.dto.ProduktDTO;
import galamb.novyeshop.services.ProduktService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produkty")
public class ProduktController {
    @Autowired
    private ProduktService produktService;

    @GetMapping
    public List<ProduktDTO> getAllProducts() {
        return produktService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProduktDTO getProductById(@PathVariable Long id) {
        return produktService.getProductById(id);
    }

    @PostMapping
    public ProduktDTO createProduct(@RequestBody ProduktDTO produktDTO) {
        return produktService.createProduct(produktDTO);
    }

    @PutMapping("/{id}")
    public ProduktDTO updateProduct(@PathVariable Long id, @RequestBody ProduktDTO produktDTO) {
        return produktService.updateProduct(id, produktDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        produktService.deleteProduct(id);
    }
}
