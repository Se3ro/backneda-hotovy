package galamb.novyeshop.repositoryl;


import galamb.novyeshop.entity.SberacKontaktu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SberacKontaktuRepository extends JpaRepository<SberacKontaktu, Long> {
    List<SberacKontaktu> findByOrderByDatumOdeslaniDesc();  // Seřazení záznamů podle data odeslání
}