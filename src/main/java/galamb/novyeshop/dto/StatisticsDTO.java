package galamb.novyeshop.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class StatisticsDTO {
    private long pocetProduktu;
    private long pocetObjednavek;
    private BigDecimal celkoveVydelky;
    private long pocetUnikatnichZakazniku;
    private List<String> nejprodavanejsiProdukty;
    private double prumernaHodnotaObjednavky;
    private double konverzniMira;
    private BigDecimal prumernaHodnotaZakaznika;
    private long pocetOpakovanychObjednavek;
    private List<String> topZakaznici;
    private Map<String, BigDecimal> nejlepsiKategorie;

    public StatisticsDTO(long pocetProduktu, long pocetObjednavek, BigDecimal celkoveVydelky,
                         long pocetUnikatnichZakazniku, List<String> nejprodavanejsiProdukty,
                         double prumernaHodnotaObjednavky, double konverzniMira,
                         BigDecimal prumernaHodnotaZakaznika, long pocetOpakovanychObjednavek,
                         List<String> topZakaznici, Map<String, BigDecimal> nejlepsiKategorie) {
        this.pocetProduktu = pocetProduktu;
        this.pocetObjednavek = pocetObjednavek;
        this.celkoveVydelky = celkoveVydelky;
        this.pocetUnikatnichZakazniku = pocetUnikatnichZakazniku;
        this.nejprodavanejsiProdukty = nejprodavanejsiProdukty;
        this.prumernaHodnotaObjednavky = prumernaHodnotaObjednavky;
        this.konverzniMira = konverzniMira;
        this.prumernaHodnotaZakaznika = prumernaHodnotaZakaznika;
        this.pocetOpakovanychObjednavek = pocetOpakovanychObjednavek;
        this.topZakaznici = topZakaznici;
        this.nejlepsiKategorie = nejlepsiKategorie;
    }
}