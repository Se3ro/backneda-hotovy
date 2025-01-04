package galamb.novyeshop.mappers;

import galamb.novyeshop.dto.PolozkaKosikuDTO;
import galamb.novyeshop.entity.PolozkaKosiku;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PolozkaKosikuMapper {
    PolozkaKosikuDTO toDTO(PolozkaKosiku polozkaKosiku);
    PolozkaKosiku toEntity(PolozkaKosikuDTO polozkaKosikuDTO);
}
