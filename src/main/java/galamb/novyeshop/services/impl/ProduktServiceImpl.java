package galamb.novyeshop.services.impl;

import galamb.novyeshop.dto.ProduktDTO;
import galamb.novyeshop.entity.Produkt;
import galamb.novyeshop.mappers.ProduktMapper;
import galamb.novyeshop.repositoryl.ProduktRepozitar;
import galamb.novyeshop.services.ProduktService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProduktServiceImpl implements ProduktService {
    @Autowired
    private ProduktRepozitar produktRepozitar;

    @Autowired
    private ProduktMapper produktMapper;

    @Override
    public List<ProduktDTO> getAllProducts() {
        return produktRepozitar.findAll()
                .stream()
                .map(produktMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProduktDTO getProductById(Long id) {
        Produkt produkt = produktRepozitar.findById(id)
                .orElseThrow(() -> new RuntimeException("Produkt nenalezen."));
        return produktMapper.toDTO(produkt);
    }

    @Override
    public ProduktDTO createProduct(ProduktDTO produktDTO) {
        Produkt produkt = produktMapper.toEntity(produktDTO);
        return produktMapper.toDTO(produktRepozitar.save(produkt));
    }

    @Override
    public ProduktDTO updateProduct(Long id, ProduktDTO produktDTO) {
        Produkt produkt = produktRepozitar.findById(id)
                .orElseThrow(() -> new RuntimeException("Produkt nenalezen."));
        produkt.setNazev(produktDTO.getNazev());
        produkt.setPopis(produktDTO.getPopis());
        produkt.setCena(produktDTO.getCena());
        produkt.setKategorie(produktDTO.getKategorie());
        produkt.setObrazek(produktDTO.getObrazek());
        return produktMapper.toDTO(produktRepozitar.save(produkt));
    }

    @Override
    public void deleteProduct(Long id) {
        produktRepozitar.deleteById(id);
    }
}
