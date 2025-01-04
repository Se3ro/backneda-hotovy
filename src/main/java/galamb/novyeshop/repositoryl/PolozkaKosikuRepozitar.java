package galamb.novyeshop.repositoryl;

import galamb.novyeshop.entity.PolozkaKosiku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PolozkaKosikuRepozitar extends JpaRepository<PolozkaKosiku, Long> {
    Optional<PolozkaKosiku> findByProduktId(Long produktId);
    void deleteAll();

}
