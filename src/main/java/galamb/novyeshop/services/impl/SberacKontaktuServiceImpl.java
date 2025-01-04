package galamb.novyeshop.services.impl;

import galamb.novyeshop.dto.SberacKontaktuDTO;
import galamb.novyeshop.entity.SberacKontaktu;
import galamb.novyeshop.mappers.SberacKontaktuMapper;
import galamb.novyeshop.repositoryl.SberacKontaktuRepository;
import galamb.novyeshop.services.SberacKontaktuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SberacKontaktuServiceImpl implements SberacKontaktuService {

    @Autowired
    private SberacKontaktuRepository sberacKontaktuRepository;

    @Autowired
    private SberacKontaktuMapper sberacKontaktuMapper;
    @Override
    public SberacKontaktuDTO saveKontakt(SberacKontaktuDTO sberacKontaktuDTO) {
        System.out.println("DTO před mapováním: " + sberacKontaktuDTO); // Ladicí výpis
        SberacKontaktu sberacKontaktu = sberacKontaktuMapper.toEntity(sberacKontaktuDTO);
        System.out.println("Entita před uložením: " + sberacKontaktu); // Ladicí výpis

        SberacKontaktu ulozenyKontakt = sberacKontaktuRepository.save(sberacKontaktu);
        System.out.println("Entita po uložení: " + ulozenyKontakt); // Ladicí výpis

        return sberacKontaktuMapper.toDTO(ulozenyKontakt);
    }


    @Override
    public List<SberacKontaktuDTO> getAllSberaciKontaktu() {
        // Načtení všech záznamů z databáze, seřazených podle data
        List<SberacKontaktu> kontakty = sberacKontaktuRepository.findByOrderByDatumOdeslaniDesc();

        // Mapa seznamu entit na seznam DTO
        return kontakty.stream()
                .map(sberacKontaktuMapper::toDTO)
                .collect(Collectors.toList());
    }
}
