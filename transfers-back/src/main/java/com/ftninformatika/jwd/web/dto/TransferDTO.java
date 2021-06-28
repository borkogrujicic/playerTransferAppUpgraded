package com.ftninformatika.jwd.web.dto;

import javax.validation.constraints.Positive;

public class TransferDTO {
	
	private Long id;
	private Long igracId;
	private String igracIme;
	
	private Long klubId;
	private String klubNaziv;
	
	@Positive(message = "Iznos transfera mora biti pozitivan broj")
	private double iznos;

	public TransferDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIgracId() {
		return igracId;
	}

	public void setIgracId(Long igracId) {
		this.igracId = igracId;
	}

	public String getIgracIme() {
		return igracIme;
	}

	public void setIgracIme(String igracIme) {
		this.igracIme = igracIme;
	}

	public Long getKlubId() {
		return klubId;
	}

	public void setKlubId(Long klubId) {
		this.klubId = klubId;
	}

	public String getKlubNaziv() {
		return klubNaziv;
	}

	public void setKlubNaziv(String klubNaziv) {
		this.klubNaziv = klubNaziv;
	}

	public double getIznos() {
		return iznos;
	}

	public void setIznos(double iznos) {
		this.iznos = iznos;
	}
	
	
	

}
