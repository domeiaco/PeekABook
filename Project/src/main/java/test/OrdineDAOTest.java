package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import model.Ordine;
import model.OrdineDAO;
import model.Utente;

public class OrdineDAOTest {
	
	OrdineDAO ordineDAO = new OrdineDAO();

	@Test
	public void doRetrieveAllOrdiniTest() {
		assertTrue(ordineDAO.doRetrieveAllOrdini(0, 1000).size()==3);
		assertTrue(ordineDAO.doRetrieveAllOrdini(0, 1000).get(0).getStato().equalsIgnoreCase("in consegna"));
	}
	
	@Test
	public void doRetrieveOrdineByNumeroTest() {
		assertTrue(ordineDAO.doRetrieveOrdineByNumero(3).getCivico()==45);
		assertTrue(ordineDAO.doRetrieveOrdineByNumero(100)==null);
	}
	
	@Test
	public void doRetrieveOrdiniByUtenteTest() {
		Utente utente = mock(Utente.class);
		when(utente.getId()).thenReturn(2);
		assertTrue(ordineDAO.doRetrieveOrdiniByUtente(utente).size()==2);
		assertFalse(ordineDAO.doRetrieveOrdiniByUtente(utente).get(1).getStato().equalsIgnoreCase("in consegna"));
	}
	
	@Test
	public void doUpdateStatoOrdineTest() {
		Ordine o = mock(Ordine.class);
		when(o.getStato()).thenReturn("Consegnato");
		when(o.getNumero()).thenReturn(3);
		ordineDAO.doUpdateStatoOrdine(o);
		assertTrue(ordineDAO.doRetrieveOrdineByNumero(3).getStato().equals("Consegnato"));
	}
	

}
