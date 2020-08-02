package yte.intern.application;

import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface EtkinlikMapper {

	EtkinlikDTO mapToDto(Etkinlik etkinlik);

	Etkinlik mapToEntity(EtkinlikDTO etkinlikDTO);

	List<EtkinlikDTO> mapToDto(List<Etkinlik> etkinlikList);

	List<Etkinlik> mapToEntity(List<EtkinlikDTO> etkinlikDTOList);
}
