package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import model.Utente;
import model.UtenteDAO;

public class UtenteDAOTest {

	UtenteDAO utenteDAO = new UtenteDAO();
	
	@Test
	public void doRetrieveIdByUsernameTest() {
		assertTrue(utenteDAO.doRetrieveIdByUsername("mimmo")==1);
	}
	
	@Test
	public void doDeleteUtenteTest() {
		Utente utente = mock(Utente.class);
		when(utente.getId()).thenReturn(1000);
		assertTrue(utenteDAO.doDeleteUtente(utente)==0);
	}
	
	@Test
	public void doRetrieveAllUtentiTest() {
		assertTrue(utenteDAO.doRetrieveAllUtenti(0, 1000).size()==3);
	}
	
	@Test
	public void doRetrieveByIdTest() {
		assertTrue(utenteDAO.doRetrieveById(1).getEmail().equalsIgnoreCase("d.iacomino@studenti.unisa.it"));
	}
	
	@Test
	public void doVerifyDuplicateTest() {
		Utente utente = mock(Utente.class);
		when(utente.getUsername()).thenReturn("mimmo");
		assertTrue(utenteDAO.doVerifyDuplicate(utente));
	}
	
	@Test
	public void doRetrieveByUsernamePasswordTest() {
		Utente utente = new Utente();
		String password = "Domenico_01";
		String username = "mimmo3";
		utente = utenteDAO.doRetrieveByUsernamePassword(username, password);
		assertTrue(utente.getEmail().equalsIgnoreCase("mimmo.iac@hotmail.it"));
	}

}
