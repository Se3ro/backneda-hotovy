package galamb.novyeshop.mappers;


import galamb.novyeshop.dto.SberacKontaktuDTO;
import galamb.novyeshop.entity.SberacKontaktu;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SberacKontaktuMapper {
    SberacKontaktu toEntity(SberacKontaktuDTO dto);
    SberacKontaktuDTO toDTO(SberacKontaktu entity);
}