package galamb.novyeshop.services.impl;

import galamb.novyeshop.dto.PolozkaKosikuDTO;
import galamb.novyeshop.dto.PromoKodDTO;
import galamb.novyeshop.entity.PolozkaKosiku;
import galamb.novyeshop.entity.Produkt;
import galamb.novyeshop.entity.PromoKod;
import galamb.novyeshop.mappers.PolozkaKosikuMapper;
import galamb.novyeshop.repositoryl.PolozkaKosikuRepozitar;
import galamb.novyeshop.repositoryl.ProduktRepozitar;
import galamb.novyeshop.repositoryl.PromoKodRepozitar;
import galamb.novyeshop.services.KosikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class KosikServiceImpl implements KosikService {
    @Autowired
    private PolozkaKosikuRepozitar polozkaKosikuRepozitar;
    @Autowired
    private ProduktRepozitar produktRepozitar;
    @Autowired
    private PromoKodRepozitar promoKodRepozitar;
    @Autowired
    private PolozkaKosikuMapper polozkaKosikuMapper;

    @Override
    public void addItemToCart(Long productId, int quantity) {
        Produkt produkt = produktRepozitar.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produkt nenalezen"));

        Optional<PolozkaKosiku> optionalPolozkaKosiku = polozkaKosikuRepozitar.findByProduktId(productId);

        BigDecimal sleva = BigDecimal.ZERO;
        boolean promoKodAplikovan = false;

        // Zkontroluj aktivní promo kód
        List<PolozkaKosiku> existujiciPolozky = polozkaKosikuRepozitar.findAll();
        Optional<PolozkaKosiku> aplikovanaPolozka = existujiciPolozky.stream()
                .filter(PolozkaKosiku::isPromoKodAplikovan)
                .findFirst();
        if (aplikovanaPolozka.isPresent()) {
            promoKodAplikovan = true;
            PromoKod promoKod = promoKodRepozitar.findByKod(aplikovanaPolozka.get().getPromoKod())
                    .orElseThrow(() -> new RuntimeException("Promo kód nenalezen"));
            sleva = promoKod.getSleva();
        }

        // Přidání nebo aktualizace položky v košíku
        if (optionalPolozkaKosiku.isPresent()) {
            PolozkaKosiku existujiciPolozka = optionalPolozkaKosiku.get();
            existujiciPolozka.setMnozstvi(existujiciPolozka.getMnozstvi() + quantity);
            BigDecimal novaCena = produkt.getCena().multiply(new BigDecimal(existujiciPolozka.getMnozstvi()));
            if (promoKodAplikovan) {
                novaCena = novaCena.multiply(BigDecimal.ONE.subtract(sleva));
            }
            existujiciPolozka.setCelkovaCena(novaCena);
            polozkaKosikuRepozitar.save(existujiciPolozka);
        } else {
            PolozkaKosiku novaPolozka = new PolozkaKosiku();
            novaPolozka.setProdukt(produkt);
            novaPolozka.setMnozstvi(quantity);
            BigDecimal celkovaCena = produkt.getCena().multiply(new BigDecimal(quantity));
            if (promoKodAplikovan) {
                celkovaCena = celkovaCena.multiply(BigDecimal.ONE.subtract(sleva));
            }
            novaPolozka.setCelkovaCena(celkovaCena);
            novaPolozka.setSleva(produkt.getCena().multiply(new BigDecimal(quantity)).subtract(celkovaCena));
            novaPolozka.setPromoKodAplikovan(promoKodAplikovan);
            polozkaKosikuRepozitar.save(novaPolozka);
        }
    }

    @Override
    public BigDecimal aplikovatPromoKod(String promoKod) {
        PromoKod kod = promoKodRepozitar.findByKod(promoKod)
                .orElseThrow(() -> new RuntimeException("Promo kód nenalezen"));

        if (!kod.isAktivni()) {
            throw new RuntimeException("Promo kód není aktivní.");
        }

        List<PolozkaKosiku> polozky = polozkaKosikuRepozitar.findAll();

        BigDecimal sleva = kod.getSleva();
        polozky.forEach(polozka -> {
            BigDecimal puvodniCena = polozka.getProdukt().getCena().multiply(new BigDecimal(polozka.getMnozstvi()));
            BigDecimal novaCena = puvodniCena.multiply(BigDecimal.ONE.subtract(sleva));
            polozka.setSleva(puvodniCena.subtract(novaCena));
            polozka.setCelkovaCena(novaCena);
            polozka.setPromoKod(kod.getKod());
            polozka.setPromoKodAplikovan(true);
        });

        polozkaKosikuRepozitar.saveAll(polozky);

        return polozky.stream()
                .map(PolozkaKosiku::getCelkovaCena)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public List<PolozkaKosikuDTO> getAllCartItems() {
        List<PolozkaKosiku> polozky = polozkaKosikuRepozitar.findAll();
        return polozky.stream()
                .map(polozkaKosikuMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PromoKod pridatPromoKod(PromoKodDTO promoKodDTO) {
        PromoKod promoKod = new PromoKod();
        promoKod.setKod(promoKodDTO.getKod());
        promoKod.setSleva(promoKodDTO.getSleva());
        promoKod.setAktivni(promoKodDTO.isAktivni());
        return promoKodRepozitar.save(promoKod);
    }

    @Override
    public PromoKod aktualizovatPromoKod(Long id, PromoKodDTO promoKodDTO) {
        PromoKod existujiciPromoKod = promoKodRepozitar.findById(id).orElseThrow();
        existujiciPromoKod.setKod(promoKodDTO.getKod());
        existujiciPromoKod.setSleva(promoKodDTO.getSleva());
        existujiciPromoKod.setAktivni(promoKodDTO.isAktivni());
        return promoKodRepozitar.save(existujiciPromoKod);
    }

    @Override
    public void smazatPromoKod(Long id) {
        promoKodRepozitar.deleteById(id);
    }

    @Override
    public void vymazatKosik() {
        polozkaKosikuRepozitar.deleteAll(); // Vymaže všechny položky z košíku
    }
}