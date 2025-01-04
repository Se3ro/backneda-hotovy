package galamb.novyeshop.mappers;

import galamb.novyeshop.entity.PolozkaKosiku;
import galamb.novyeshop.entity.ObjednavkaPolozka;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PolozkaKosikuToObjednavkaPolozkaMapper {

    @Mapping(source = "produkt", target = "produkt")
    @Mapping(source = "mnozstvi", target = "mnozstvi")
    @Mapping(source = "celkovaCena", target = "celkovaCena")
    @Mapping(source = "sleva", target = "sleva")
    ObjednavkaPolozka toObjednavkaPolozka(PolozkaKosiku polozkaKosiku);
}
