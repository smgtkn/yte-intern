package yte.intern.application;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EtkinlikMapper  {
	@Autowired
private EtkinlikRepository etkinlikRepository;
	
	EtkinlikDTO mapToDto(Etkinlik etkinlik) {
		EtkinlikDTO etkinlikDto=new EtkinlikDTO(etkinlik.getName(), etkinlik.getStart(), etkinlik.getEnd());
	
		return etkinlikDto;
	}

	Etkinlik mapToEntity1(EtkinlikDTO etkinlikDTO) {
		
		Etkinlik etkinlik1=new Etkinlik(etkinlikRepository.findByName(etkinlikDTO.getName()).getId(),etkinlikDTO.getName(),etkinlikDTO.getStart(),etkinlikDTO.getEnd());
		return etkinlik1;
		
	}
	Etkinlik mapToEntity(EtkinlikDTO etkinlikDTO) {
		
		Etkinlik etkinlik1=new Etkinlik(null,etkinlikDTO.getName(),etkinlikDTO.getStart(),etkinlikDTO.getEnd());
		return etkinlik1;
		
	}
	List<EtkinlikDTO> mapToDto(List<Etkinlik> etkinlikList){
		
		List <EtkinlikDTO> liste= new ArrayList<EtkinlikDTO>();
		for(Etkinlik a :etkinlikList) {
			EtkinlikDTO new1=new EtkinlikDTO(a.getName(), a.getStart(), a.getEnd());
			liste.add(new1);
			
		}
		
		return liste;
		
	}

	List<Etkinlik> mapToEntity(List<EtkinlikDTO> etkinlikDTOList) {
		List <Etkinlik> liste= new ArrayList<Etkinlik>();
		for(EtkinlikDTO a :etkinlikDTOList) {
			Etkinlik new1=new Etkinlik(null,a.getName(), a.getStart(), a.getEnd());
			liste.add(new1);
			
		}
		
		return liste;
		
	}
}
