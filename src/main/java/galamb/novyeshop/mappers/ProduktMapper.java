package galamb.novyeshop.mappers;

import galamb.novyeshop.dto.ProduktDTO;
import galamb.novyeshop.entity.Produkt;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProduktMapper {
    ProduktDTO toDTO(Produkt produkt);
    Produkt toEntity(ProduktDTO produktDTO);
}
