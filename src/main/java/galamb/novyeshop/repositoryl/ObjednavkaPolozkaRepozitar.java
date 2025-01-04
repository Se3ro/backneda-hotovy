package galamb.novyeshop.repositoryl;

import galamb.novyeshop.entity.ObjednavkaPolozka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjednavkaPolozkaRepozitar extends JpaRepository<ObjednavkaPolozka, Long> {
}
