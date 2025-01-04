package galamb.novyeshop.repositoryl;

import galamb.novyeshop.entity.Uzivatel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UzivatelRepozitar extends JpaRepository<Uzivatel, Long> {
    Optional<Uzivatel> findByEmail(String email);
}
