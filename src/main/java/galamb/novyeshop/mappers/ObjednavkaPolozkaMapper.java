package galamb.novyeshop.mappers;

import galamb.novyeshop.dto.ObjednavkaPolozkaDTO;
import galamb.novyeshop.entity.ObjednavkaPolozka;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ObjednavkaPolozkaMapper {
    @Mapping(source = "produkt.id", target = "produktId")
    @Mapping(source = "objednavka.id", target = "objednavkaId")
    ObjednavkaPolozkaDTO toDTO(ObjednavkaPolozka objednavkaPolozka);

    @Mapping(source = "produktId", target = "produkt.id")
    @Mapping(source = "objednavkaId", target = "objednavka.id")
    ObjednavkaPolozka toEntity(ObjednavkaPolozkaDTO objednavkaPolozkaDTO);
}
