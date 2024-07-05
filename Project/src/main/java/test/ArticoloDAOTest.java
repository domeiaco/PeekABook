package test;
import org.junit.BeforeClass;
import org.junit.Test;
import model.Articolo;
import model.ArticoloDAO;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;;

public class ArticoloDAOTest {

	ArticoloDAO articoloDAO = new ArticoloDAO();

	@BeforeClass 
    public static void setUpClass() {      
        System.out.println("ArticoloDAO TEST SETUP");
    }
	
	@Test
	public void doRetrieveArticoliByCodiceTest() {
		Articolo a = articoloDAO.doRetrieveArticoliByCodice(16);
		assertTrue(a.getNome().equalsIgnoreCase("Dracula"));
	}
	
	@Test
	public void doRemoveArticoloTest() {
		int n = articoloDAO.doRemoveArticolo(100);
		assertTrue(n==0);
	}
	
	@Test
	public void doRetrieveArticoliTest() {
		List<Articolo> list = new ArrayList<>();
		list = articoloDAO.doRetrieveArticoli(0,1000);
		assertTrue(list.size()==26);
	}
	
	@Test
	public void doRetrieveArticoliByNomeLikeTest() {
		List<Articolo> list = new ArrayList<>();
		list = articoloDAO.doRetrieveArticoliByNomeLike("del",0,1000);
		assertTrue(list.size()==5);
	}
	
	@Test
	public void doUpdateArticoloTest() {
		Articolo a = new Articolo();
		int n = articoloDAO.doUpdateArticolo(200,100,200);
		assertTrue(n==0);
	}
	
	@Test
	public void doUpdateArticoloFullTest() {
		Articolo a = articoloDAO.doRetrieveArticoliByCodice(2);
		a.setQuantita(200);
		articoloDAO.doUpdateArticoloFull(a);
		assertTrue(articoloDAO.doRetrieveArticoliByCodice(2).getQuantita()==200);
	}


}
