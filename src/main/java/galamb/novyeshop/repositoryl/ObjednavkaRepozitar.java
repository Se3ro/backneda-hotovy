package galamb.novyeshop.repositoryl;

import galamb.novyeshop.entity.Objednavka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjednavkaRepozitar extends JpaRepository<Objednavka, Long> {
    void deleteAll();
}