package model;

import java.util.*;

public class Carrello {
	private double totale;
	private int numeroArticoli;
	private int dimensione;
	private Utente utente;
	private LinkedHashMap<Articolo, Integer> articoli;
	
	public Carrello() {
		articoli = new LinkedHashMap<>();
	}
	
	public double getTotale(){
		return totale;
	}
	
	public void setTotale(double totale) {
		this.totale = totale;
	}
	
	public int getNumeroArticoli() {
		return numeroArticoli;
	}
	
	public void setNumeroArticoli(int numeroArticoli) {
		this.numeroArticoli = numeroArticoli;
	}
	
	public Utente getUtente() {
		return utente;
	}
	
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	
	public LinkedHashMap<Articolo, Integer> getArticoli(){
		return articoli;
	}
	
	public Set<Articolo> getInnerArticoli(){
		return articoli.keySet();
	}
	
	public void setArticoli(LinkedHashMap<Articolo, Integer> articoli) {
		this.articoli = articoli;
		Set<Articolo> keys = articoli.keySet();
		double tot=0;
		int num=0;
		for(Articolo key : keys) {
			tot+=((key.getPrezzo())*articoli.get(key));
			num = num+articoli.get(key); 
		}
		tot = Math.round(tot * 100.0) / 100.0;
		setTotale(tot);
		setNumeroArticoli(num);
	}
	
	public int getDimensione() {
		return getInnerArticoli().size();
	}
	
	public void setDimensione(int dimensione) {
		this.dimensione = dimensione;
	}
}
