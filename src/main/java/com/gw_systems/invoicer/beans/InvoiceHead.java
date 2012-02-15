package com.gw_systems.invoicer.beans;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class InvoiceHead {

	@Id
	@SequenceGenerator(allocationSize=1, initialValue=0, name="INVHEAD_SEQ", sequenceName="INVHEAD_SEQ")
	@GeneratedValue(generator="INVHEAD_SEQ", strategy=GenerationType.SEQUENCE)
	private int id;
	
	@ManyToOne
	private Customer kiallito;
	
	@ManyToOne
	private Customer vevo;
	
	@Column
	private String sorszam;
	
	@Column
	private boolean storno;
	
	@Column
	private Timestamp kiallitas;
	
	@Column
	private Timestamp teljesites;
	
	@Column
	private Timestamp hatarido;
	
	@Column
	private String fizetesiMod;
	
	@OneToMany
	private List<InvoiceItem> items;

	public InvoiceHead(Customer kiallito, Customer vevo, String sorszam,
			boolean storno, Timestamp kiallitas, Timestamp teljesites,
			Timestamp hatarido, String fizetesiMod) {
		this.kiallito = kiallito;
		this.vevo = vevo;
		this.sorszam = sorszam;
		this.storno = storno;
		this.kiallitas = kiallitas;
		this.teljesites = teljesites;
		this.hatarido = hatarido;
		this.fizetesiMod = fizetesiMod;
	}
	
	public InvoiceHead() {}

	public Customer getKiallito() {
		return kiallito;
	}

	public void setKiallito(Customer kiallito) {
		this.kiallito = kiallito;
	}

	public Customer getVevo() {
		return vevo;
	}

	public void setVevo(Customer vevo) {
		this.vevo = vevo;
	}

	public String getSorszam() {
		return sorszam;
	}

	public void setSorszam(String sorszam) {
		this.sorszam = sorszam;
	}

	public boolean isStorno() {
		return storno;
	}

	public void setStorno(boolean storno) {
		this.storno = storno;
	}

	public Timestamp getKiallitas() {
		return kiallitas;
	}

	public void setKiallitas(Timestamp kiallitas) {
		this.kiallitas = kiallitas;
	}

	public Timestamp getTeljesites() {
		return teljesites;
	}

	public void setTeljesites(Timestamp teljesites) {
		this.teljesites = teljesites;
	}

	public Timestamp getHatarido() {
		return hatarido;
	}

	public void setHatarido(Timestamp hatarido) {
		this.hatarido = hatarido;
	}

	public String getFizetesiMod() {
		return fizetesiMod;
	}

	public void setFizetesiMod(String fizetesiMod) {
		this.fizetesiMod = fizetesiMod;
	}

	public List<InvoiceItem> getItems() {
		return items;
	}

	public void setItems(List<InvoiceItem> items) {
		this.items = items;
	}

	public int getId() {
		return id;
	}	
}
