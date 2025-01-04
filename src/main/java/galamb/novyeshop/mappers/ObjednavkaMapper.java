package galamb.novyeshop.mappers;

import galamb.novyeshop.dto.ObjednavkaDTO;
import galamb.novyeshop.entity.Objednavka;
import galamb.novyeshop.entity.emums.StavObjednavky;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ObjednavkaMapper {

    @Mapping(target = "stav", source = "stav", qualifiedByName = "enumToString")
    ObjednavkaDTO toDTO(Objednavka objednavka);

    @Mapping(target = "stav", source = "stav", qualifiedByName = "stringToEnum")
    Objednavka toEntity(ObjednavkaDTO objednavkaDTO);

    @Named("enumToString")
    static String enumToString(StavObjednavky stav) {
        return stav != null ? stav.name() : null;
    }

    @Named("stringToEnum")
    static StavObjednavky stringToEnum(String stav) {
        return stav != null ? StavObjednavky.valueOf(stav) : null;
    }
}
