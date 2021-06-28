package com.ftninformatika.jwd.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftninformatika.jwd.model.Igrac;
import com.ftninformatika.jwd.model.Klub;
import com.ftninformatika.jwd.model.Transfer;
import com.ftninformatika.jwd.service.IgracService;
import com.ftninformatika.jwd.service.KlubService;
import com.ftninformatika.jwd.service.TransferService;
import com.ftninformatika.jwd.support.TransferDtoToTransfer;
import com.ftninformatika.jwd.support.TransferToTransferDto;
import com.ftninformatika.jwd.web.dto.TransferDTO;

@RestController
@RequestMapping(value = "/api/transferi", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransferController {
	
	@Autowired
	private TransferService transferService;
	
	@Autowired
	private KlubService klubService;
	
	@Autowired
	private IgracService igracService;
	
	@Autowired
	private TransferDtoToTransfer toTransfer;
	
	@Autowired
	private TransferToTransferDto toDto;
	
    @GetMapping("/{id}")
    public ResponseEntity<TransferDTO> getOne(@PathVariable Long id){
       Transfer transfer = transferService.findOne(id);
       

        if(transfer != null) {
            return new ResponseEntity<>(toDto.convert(transfer), HttpStatus.OK);
        }else {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransferDTO> create(@Valid @RequestBody TransferDTO dto){
    	Klub klub = klubService.findOne(dto.getKlubId());
    	Igrac igrac = igracService.findOne(dto.getIgracId());
    	
    	if (igrac.isNaProdaju() == false) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	
    	if (dto.getIznos() > klub.getBudzet()) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	
        Transfer transfer = toTransfer.convert(dto);
        Transfer saved = transferService.save(transfer);

        return new ResponseEntity<>(toDto.convert(saved), HttpStatus.CREATED);
    }

}
