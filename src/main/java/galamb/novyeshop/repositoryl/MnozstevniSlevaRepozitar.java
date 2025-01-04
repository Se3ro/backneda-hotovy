package galamb.novyeshop.repositoryl;

import galamb.novyeshop.entity.MnozstevniSleva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MnozstevniSlevaRepozitar extends JpaRepository<MnozstevniSleva, Long> {
    Optional<MnozstevniSleva> findFirstByMnozstviLessThanEqualOrderByMnozstviDesc(int mnozstvi);
}
