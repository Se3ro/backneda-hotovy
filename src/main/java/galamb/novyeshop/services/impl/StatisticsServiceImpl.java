package galamb.novyeshop.services.impl;

import galamb.novyeshop.dto.StatisticsDTO;
import galamb.novyeshop.entity.Objednavka;
import galamb.novyeshop.repositoryl.ObjednavkaRepozitar;
import galamb.novyeshop.repositoryl.ProduktRepozitar;
import galamb.novyeshop.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private ProduktRepozitar produktRepozitar;

    @Autowired
    private ObjednavkaRepozitar objednavkaRepozitar;

    @Override
    public StatisticsDTO calculateStatistics() {
        long pocetProduktu = produktRepozitar.count();
        long pocetObjednavek = objednavkaRepozitar.count();
        BigDecimal celkoveVydelky = objednavkaRepozitar.findAll()
                .stream()
                .map(Objednavka::getCelkovaCena)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        long pocetUnikatnichZakazniku = objednavkaRepozitar.findAll()
                .stream()
                .map(Objednavka::getEmail)
                .distinct()
                .count();
        List<String> nejprodavanejsiProdukty = objednavkaRepozitar.findAll()
                .stream()
                .flatMap(objednavka -> objednavka.getPolozky().stream())
                .collect(Collectors.groupingBy(
                        polozka -> polozka.getProdukt().getNazev(),
                        Collectors.summingInt(polozka -> polozka.getMnozstvi())
                ))
                .entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        double prumernaHodnotaObjednavky = pocetObjednavek > 0
                ? celkoveVydelky.doubleValue() / pocetObjednavek
                : 0.0;

        double konverzniMira = (double) pocetObjednavek / 1000 * 100;
        BigDecimal prumernaHodnotaZakaznika = pocetUnikatnichZakazniku > 0
                ? celkoveVydelky.divide(BigDecimal.valueOf(pocetUnikatnichZakazniku), 2, RoundingMode.HALF_UP)  // Opraveno zaokrouhlování
                : BigDecimal.ZERO;
        long pocetOpakovanychObjednavek = objednavkaRepozitar.findAll()
                .stream()
                .collect(Collectors.groupingBy(Objednavka::getEmail, Collectors.counting()))
                .values()
                .stream()
                .filter(pocet -> pocet > 1)
                .count();
        List<String> topZakaznici = objednavkaRepozitar.findAll()
                .stream()
                .collect(Collectors.groupingBy(Objednavka::getEmail, Collectors.reducing(
                        BigDecimal.ZERO, Objednavka::getCelkovaCena, BigDecimal::add)))
                .entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        Map<String, BigDecimal> nejlepsiKategorie = objednavkaRepozitar.findAll()
                .stream()
                .flatMap(objednavka -> objednavka.getPolozky().stream())
                .collect(Collectors.groupingBy(
                        polozka -> polozka.getProdukt().getKategorie().name(),  // Opraveno na String
                        Collectors.reducing(BigDecimal.ZERO, polozka -> polozka.getProdukt().getCena().multiply(BigDecimal.valueOf(polozka.getMnozstvi())), BigDecimal::add)
                ));


        return new StatisticsDTO(
                pocetProduktu,
                pocetObjednavek,
                celkoveVydelky,
                pocetUnikatnichZakazniku,
                nejprodavanejsiProdukty,
                prumernaHodnotaObjednavky,
                konverzniMira,
                prumernaHodnotaZakaznika,
                pocetOpakovanychObjednavek,
                topZakaznici,
                nejlepsiKategorie

        );
    }
}
