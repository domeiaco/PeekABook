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
		Set<Articolo> innerArticoli = articoli.keySet();
		return innerArticoli;
	}
	
	public void setArticoli(LinkedHashMap<Articolo, Integer> articoli) {
		this.articoli = articoli;
		Set<Articolo> keys = articoli.keySet();
		double totale=0;
		int num=0;
		for(Articolo key : keys) {
			totale+=((key.getPrezzo())*articoli.get(key));
			num = num+articoli.get(key); 
		}
		totale = Math.round(totale * 100.0) / 100.0;
		setTotale(totale);
		setNumeroArticoli(num);
	}
	
	public int getDimensione() {
		return getInnerArticoli().size();
	}
	
	public void setDimensione(int dimensione) {
		this.dimensione = dimensione;
	}
}