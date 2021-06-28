package com.ftninformatika.jwd.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Klub {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column (nullable = false, unique = true)
    private String naziv;
    
    @Column (nullable = false)
    private double budzet;
    
    @OneToMany(mappedBy = "klub", cascade = CascadeType.ALL) 
    private List <Igrac> igraci = new ArrayList<>();
    
    @OneToMany(mappedBy = "klub", cascade = CascadeType.ALL) 
    private List <Transfer> transferi = new ArrayList<>();

	public Klub() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public double getBudzet() {
		return budzet;
	}

	public void setBudzet(double budzet) {
		this.budzet = budzet;
	}

	public List<Igrac> getIgraci() {
		return igraci;
	}

	public void setIgraci(List<Igrac> igraci) {
		this.igraci = igraci;
	}
    
    public void dodajIgraca (Igrac igrac) {
    	this.igraci.add(igrac);
        if (!equals(igrac.getKlub())) {
        	igrac.setKlub(this);
        }
    }
    
	public void dodajTransfer (Transfer transfer) {
    	this.transferi.add(transfer);
    	if(!equals(transfer.getKlub())) {
    		transfer.setKlub(this);
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
		Klub other = (Klub) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Klub [id=" + id + ", naziv=" + naziv + ", budzet=" + budzet + ", igraci=" + igraci + ", transferi="
				+ transferi + "]";
	}
	
	


}
