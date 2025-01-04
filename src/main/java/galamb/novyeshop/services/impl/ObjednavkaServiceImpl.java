    package galamb.novyeshop.services.impl;

    import galamb.novyeshop.dto.ObjednavkaDTO;
    import galamb.novyeshop.entity.Objednavka;
    import galamb.novyeshop.entity.PolozkaKosiku;
    import galamb.novyeshop.entity.ObjednavkaPolozka;
    import galamb.novyeshop.entity.emums.StavObjednavky;
    import galamb.novyeshop.mappers.ObjednavkaMapper;
    import galamb.novyeshop.repositoryl.ObjednavkaRepozitar;
    import galamb.novyeshop.repositoryl.PolozkaKosikuRepozitar;
    import galamb.novyeshop.services.ObjednavkaService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;

    import java.math.BigDecimal;
    import java.time.LocalDateTime;
    import java.util.List;
    import java.util.stream.Collectors;
    @Service
    public class ObjednavkaServiceImpl implements ObjednavkaService {

        @Autowired
        private ObjednavkaRepozitar objednavkaRepository;

        @Autowired
        private PolozkaKosikuRepozitar polozkaKosikuRepozitar;

        @Autowired
        private ObjednavkaMapper objednavkaMapper;

        @Override
        @Transactional
        public ObjednavkaDTO vytvoritObjednavku(ObjednavkaDTO objednavkaDTO) {
            // 1. Získání všech položek z košíku
            List<PolozkaKosiku> polozkyKosiku = polozkaKosikuRepozitar.findAll();

            if (polozkyKosiku.isEmpty()) {
                throw new RuntimeException("Košík je prázdný!");
            }

            // 2. Výpočet celkové ceny a uložení položek do objednávky
            BigDecimal celkovaCena = polozkyKosiku.stream()
                    .map(PolozkaKosiku::getCelkovaCena)  // Pouze sebereme celkovou cenu položky
                    .reduce(BigDecimal.ZERO, BigDecimal::add);  // Sečteme všechny ceny položek
            objednavkaDTO.setCelkovaCena(celkovaCena);

            // 3. Mapování DTO na entitu
            Objednavka objednavka = objednavkaMapper.toEntity(objednavkaDTO);

            // Ruční mapování položek objednávky
            List<ObjednavkaPolozka> objednavkaPolozky = polozkyKosiku.stream()
                    .map(polozka -> {
                        ObjednavkaPolozka objednavkaPolozka = new ObjednavkaPolozka();
                        objednavkaPolozka.setObjednavka(objednavka); // přiřazení objednávky
                        objednavkaPolozka.setProdukt(polozka.getProdukt()); // přiřazení produktu
                        objednavkaPolozka.setMnozstvi(polozka.getMnozstvi());
                        objednavkaPolozka.setCelkovaCena(polozka.getCelkovaCena());
                        objednavkaPolozka.setSleva(polozka.getSleva()); // Uložení slevy

                        return objednavkaPolozka;
                    })
                    .collect(Collectors.toList());

            // 4. Nastavení data a času odeslání
            objednavka.setDatumOdeslani(LocalDateTime.now());
            objednavka.setStav(StavObjednavky.NOVA); // Výchozí stav objednávky

            // 5. Uložení objednávky a položek objednávky do databáze
            objednavka.setPolozky(objednavkaPolozky);
            Objednavka ulozenaObjednavka = objednavkaRepository.save(objednavka);

            // 6. Vrácení uložené objednávky jako DTO
            return objednavkaMapper.toDTO(ulozenaObjednavka);
        }

        @Override
        public List<ObjednavkaDTO> getObjednavky() {
            // Získání všech objednávek a jejich mapování na DTO
            return objednavkaRepository.findAll()
                    .stream()
                    .map(objednavkaMapper::toDTO)
                    .collect(Collectors.toList());
        }


    }
