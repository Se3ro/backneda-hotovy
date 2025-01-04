package galamb.novyeshop.repositoryl;

import galamb.novyeshop.entity.PromoKod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PromoKodRepozitar extends JpaRepository<PromoKod, Long> {
    Optional<PromoKod> findByKod(String kod);

}
