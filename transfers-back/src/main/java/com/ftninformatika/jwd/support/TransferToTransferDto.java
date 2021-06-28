package com.ftninformatika.jwd.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.model.Transfer;
import com.ftninformatika.jwd.web.dto.TransferDTO;

@Component
public class TransferToTransferDto implements Converter <Transfer, TransferDTO>{
	

	@Override
	public TransferDTO convert(Transfer transfer) {
		TransferDTO dto = new TransferDTO();
		dto.setId(transfer.getId());
		dto.setIgracId(transfer.getIgrac().getId());
		dto.setIgracIme(transfer.getIgrac().getIme());
		dto.setKlubId(transfer.getKlub().getId());
		dto.setKlubNaziv(transfer.getKlub().getNaziv());
		dto.setIznos(transfer.getIznos());
		return dto;
	}

}
