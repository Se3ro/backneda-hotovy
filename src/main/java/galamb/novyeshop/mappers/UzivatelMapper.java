package galamb.novyeshop.mappers;

import galamb.novyeshop.dto.UzivatelDTO;
import galamb.novyeshop.entity.Uzivatel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UzivatelMapper {

    UzivatelDTO toDTO(Uzivatel uzivatel);

    Uzivatel toEntity(UzivatelDTO uzivatelDTO);
}
