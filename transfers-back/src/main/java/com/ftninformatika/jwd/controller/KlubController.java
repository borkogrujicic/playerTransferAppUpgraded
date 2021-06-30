package com.ftninformatika.jwd.controller;

import java.util.List;

import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftninformatika.jwd.model.Igrac;
import com.ftninformatika.jwd.model.Klub;
import com.ftninformatika.jwd.service.IgracService;
import com.ftninformatika.jwd.service.KlubService;
import com.ftninformatika.jwd.support.IgracToIgracDto;
import com.ftninformatika.jwd.support.KlubToKlubDto;
import com.ftninformatika.jwd.web.dto.IgracDTO;
import com.ftninformatika.jwd.web.dto.KlubDTO;

@RestController
@RequestMapping(value = "/api/klubovi", produces = MediaType.APPLICATION_JSON_VALUE)
public class KlubController {
	
	@Autowired
	private KlubService klubService;
	
	@Autowired
	private IgracService igracService;
	
	@Autowired
	private KlubToKlubDto toDto;
	
	@Autowired
	private IgracToIgracDto toIgracDto;
	
	
	
	@GetMapping
    public ResponseEntity<List<KlubDTO>> getAll(@RequestParam(value = "pageNo", defaultValue = "0") int pageNo){

    	Page <Klub> klubovi = klubService.findAll(pageNo);
    	
        HttpHeaders headers = new HttpHeaders();
        headers.add("Total-Pages", Integer.toString(klubovi.getTotalPages()));


        return new ResponseEntity<>(toDto.convert(klubovi.getContent()), headers, HttpStatus.OK);
    }
	
    @GetMapping("/{id}")
    public ResponseEntity<KlubDTO> getOne(@PathVariable Long id){
       Klub klub = klubService.findOne(id);

        if(klub != null) {
            return new ResponseEntity<>(toDto.convert(klub), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/{id}/details")
    public ResponseEntity<List<IgracDTO>> findByFilmId(@PathVariable @Positive(message = "Id must be positive.")  Long id){
        List<Igrac> igraci = igracService.findByKlub(id);

        return new ResponseEntity<>(toIgracDto.convert(igraci), HttpStatus.OK);
    }

}
