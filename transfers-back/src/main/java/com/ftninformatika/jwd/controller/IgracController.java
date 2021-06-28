package com.ftninformatika.jwd.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftninformatika.jwd.model.Igrac;

import com.ftninformatika.jwd.service.IgracService;
import com.ftninformatika.jwd.support.IgracDtoToIgrac;
import com.ftninformatika.jwd.support.IgracToIgracDto;
import com.ftninformatika.jwd.web.dto.IgracDTO;


@RestController
@RequestMapping(value = "/api/igraci", produces = MediaType.APPLICATION_JSON_VALUE)
public class IgracController {
	
	
	@Autowired
	private IgracService igracService;
	
	@Autowired
	private IgracDtoToIgrac toIgrac;
	
	@Autowired
	private IgracToIgracDto toDto;
	
	
//    @GetMapping
//    public ResponseEntity<List<IgracDTO>> getAll(
//            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo){
//
//    	Page<Igrac> page = igracService.findAll(pageNo);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Total-Pages", Integer.toString(page.getTotalPages()));
//
//        return new ResponseEntity<>(toDto.convert(page.getContent()),headers, HttpStatus.OK);
//    }
    
	@PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IgracDTO> create(@Valid @RequestBody IgracDTO dto){
    	
        Igrac igrac = toIgrac.convert(dto);
        Igrac saved = igracService.save(igrac);

        return new ResponseEntity<>(toDto.convert(saved), HttpStatus.CREATED);
    }
    
    @PreAuthorize("permitAll()")
    @GetMapping
    public ResponseEntity<List<IgracDTO>> getAll(
  		  @RequestParam(value = "pozicija", required = false) String pozicija,
  		  @RequestParam(value = "klubNaziv", required = false) String klubNaziv,
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo){

    	 Page<Igrac> page = null;
    	 
    	 if (pozicija != null || klubNaziv != null) {
    		 page = igracService.search(pozicija, klubNaziv, pageNo);
    	 }else {
    		 page = igracService.findAll(pageNo);
    	 }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Total-Pages", Integer.toString(page.getTotalPages()));

        return new ResponseEntity<>(toDto.convert(page.getContent()),headers, HttpStatus.OK);
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<IgracDTO> getOne(@PathVariable Long id){
       Igrac igrac = igracService.findOne(id);

        if(igrac != null) {
            return new ResponseEntity<>(toDto.convert(igrac), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IgracDTO> update(@PathVariable Long id, @Valid @RequestBody IgracDTO dto){

        if(!id.equals(dto.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Igrac linija = toIgrac.convert(dto);
        Igrac saved = igracService.update(linija);

        return new ResponseEntity<>(toDto.convert(saved),HttpStatus.OK);
    }
    
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Igrac deleted = igracService.delete(id);

        if(deleted != null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
	

}
