package galamb.novyeshop.services;

import galamb.novyeshop.dto.ProduktDTO;

import java.util.List;

public interface ProduktService {
    List<ProduktDTO> getAllProducts();
    ProduktDTO getProductById(Long id);
    ProduktDTO createProduct(ProduktDTO produktDTO);
    ProduktDTO updateProduct(Long id, ProduktDTO produktDTO);
    void deleteProduct(Long id);
}
