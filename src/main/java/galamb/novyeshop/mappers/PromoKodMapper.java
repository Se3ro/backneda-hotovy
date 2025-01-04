package galamb.novyeshop.mappers;

import galamb.novyeshop.dto.PromoKodDTO;
import galamb.novyeshop.entity.PromoKod;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PromoKodMapper {
    PromoKodDTO toDTO(PromoKod promoKod);
    PromoKod toEntity(PromoKodDTO promoKodDTO);
}
