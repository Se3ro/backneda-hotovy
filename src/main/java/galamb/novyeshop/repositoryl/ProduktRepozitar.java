package galamb.novyeshop.repositoryl;

import galamb.novyeshop.entity.Produkt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduktRepozitar extends JpaRepository<Produkt, Long> {
}
