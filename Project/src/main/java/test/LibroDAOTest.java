package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.*;

import model.Articolo;
import model.Autore;
import model.Genere;
import model.Libro;
import model.LibroDAO;

import org.junit.Test;

public class LibroDAOTest {

	LibroDAO libroDAO = new LibroDAO();
	
	@Test
	public void doRemoveLibroTest() {
		int n = libroDAO.doRemoveLibro(200);
		assertTrue(n==0);
	}
	
	@Test
	public void doUpdateLibroTest() {
		Autore autore = mock(Autore.class);
		when(autore.getCodice()).thenReturn(1);
		when(autore.getCognome()).thenReturn("Verne");
		when(autore.getNome()).thenReturn("Jules");
		Libro l = libroDAO.doRetrieveLibroByArticolo(2);
		l.setAutore(autore);
		libroDAO.doUpdateLibro(l);
		assertTrue(libroDAO.doRetrieveLibroByArticolo(2).getAutore().getCodice()==autore.getCodice());
	}
	
	@Test
	public void doRetrieveLibroByGenereTest() {
		Genere genere = mock(Genere.class);
		when(genere.getNome()).thenReturn("Horror");
		assertTrue(libroDAO.doRetrieveLibroByGenere(genere, 0, 1000).size()==5);
	}
	
	@Test
	public void doRetrieveLibroByArticoloTest() {
		assertTrue(libroDAO.doRetrieveLibroByArticolo(1).getNome().equalsIgnoreCase("viaggio al centro della terra"));
	}
	
	@Test
	public void doRetrieveLibroTest() {
		assertTrue(libroDAO.doRetrieveLibro(0, 1000).size()==28);
	}

}
