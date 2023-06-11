package model;

import java.util.ArrayList;

public class Genere{
	private String nome;
	private ArrayList<Libro> libri;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public ArrayList<Libro> getLibri(){
		return libri;
	}
	
	public void setLibri(ArrayList<Libro> libri) {
		this.libri = libri;
	}
}