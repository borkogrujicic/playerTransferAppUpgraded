package com.ftninformatika.jwd.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.model.Klub;
import com.ftninformatika.jwd.web.dto.KlubDTO;

@Component
public class KlubToKlubDto implements Converter<Klub, KlubDTO>{

	@Override
	public KlubDTO convert(Klub klub) {
		KlubDTO dto = new KlubDTO();
		dto.setBudzet(klub.getBudzet());
		dto.setId(klub.getId());
		dto.setNaziv(klub.getNaziv());
		
		return dto;
	}
	
    public List<KlubDTO> convert(List<Klub> klubovi){
        List<KlubDTO> dtos = new ArrayList<>();

        for(Klub k : klubovi) {
        	KlubDTO dto = convert(k);
        	dtos.add(dto);
        }

        return dtos;
    }

}
