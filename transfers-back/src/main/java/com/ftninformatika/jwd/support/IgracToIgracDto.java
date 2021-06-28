package com.ftninformatika.jwd.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.model.Igrac;
import com.ftninformatika.jwd.web.dto.IgracDTO;

@Component
public class IgracToIgracDto implements Converter <Igrac, IgracDTO>{
	
	@Autowired
	private KlubToKlubDto toDto;

	@Override
	public IgracDTO convert(Igrac igrac) {
		IgracDTO dto = new IgracDTO();
		dto.setBrojDresa(igrac.getBrojDresa());
		dto.setId(igrac.getId());
		dto.setDatum(igrac.getDatum().toString());
		dto.setIme(igrac.getIme());
		dto.setKlub(toDto.convert(igrac.getKlub()));
		dto.setNaProdaju(igrac.isNaProdaju());
		dto.setPozicija(igrac.getPozicija());
		return dto;
	}
	
    public List<IgracDTO> convert(List<Igrac> igraci){
        List<IgracDTO> dtoS = new ArrayList<>();

        for(Igrac it : igraci) {
        	dtoS.add(convert(it));
        }

        return dtoS;
    }

}
