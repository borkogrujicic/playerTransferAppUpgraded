package com.ftninformatika.jwd.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;


@Entity
public class Igrac {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column (nullable = false)
    private String ime;
      
    @Column
    private String pozicija;
    
    @Column(name="broj_dresa")   
    private int brojDresa;
    
    @Column(nullable = false)
    private LocalDate datum;
    
    @Column(name="na_prodaju")
    private boolean naProdaju;
    
    @ManyToOne
    private Klub klub;
    
    @OneToMany(mappedBy = "igrac", cascade = CascadeType.ALL) 
    private List<Transfer> transferi = new ArrayList<>();

	public Igrac() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPozicija() {
		return pozicija;
	}

	public void setPozicija(String pozicija) {
		this.pozicija = pozicija;
	}

	public int getBrojDresa() {
		return brojDresa;
	}

	public void setBrojDresa(int brojDresa) {
		this.brojDresa = brojDresa;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	public boolean isNaProdaju() {
		return naProdaju;
	}

	public void setNaProdaju(boolean naProdaju) {
		this.naProdaju = naProdaju;
	}

	public Klub getKlub() {
		return klub;
	}

	public void setKlub(Klub klub) {
		this.klub = klub;
	}
	
    public void dodajTransfer (Transfer transfer) {
    	this.transferi.add(transfer);
    	if(!equals(transfer.getIgrac())) {
    		transfer.setIgrac(this);
    	}
    }
    
    

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Igrac other = (Igrac) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Igrac [id=" + id + ", ime=" + ime + ", pozicija=" + pozicija + ", brojDresa=" + brojDresa + ", datum="
				+ datum + ", naProdaju=" + naProdaju + ", klub=" + klub + "]";
	}
    
    
    		

}
