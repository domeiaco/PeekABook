package model;

import java.util.ArrayList;
import java.util.List;

public class Genere{
	private String nome;
	private List<Libro> libri;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Libro> getLibri(){
		return libri;
	}
	
	public void setLibri(List<Libro> libri) {
		this.libri = libri;
	}
}