package galamb.novyeshop.services;

import galamb.novyeshop.dto.ObjednavkaDTO;
import galamb.novyeshop.entity.emums.StavObjednavky;

import java.util.List;

public interface ObjednavkaService {

    ObjednavkaDTO vytvoritObjednavku(ObjednavkaDTO objednavkaDTO);

    List<ObjednavkaDTO> getObjednavky();

}
