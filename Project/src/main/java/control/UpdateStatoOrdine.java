package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Ordine;
import model.OrdineDAO;
import model.Utente;
import model.UtenteDAO;

/**
 * Servlet implementation class UpdateStatoOrdine
 */
@WebServlet("/UpdateStatoOrdine")
public class UpdateStatoOrdine extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer numero= Integer.parseInt(request.getParameter("numero"));
		String stato = request.getParameter("stato");
		OrdineDAO ordineDAO = new OrdineDAO();
		Ordine o = new Ordine();
		o = ordineDAO.doRetrieveOrdineByNumero(numero);
		o.setStato(stato);
		ordineDAO.doUpdateStatoOrdine(o);
		
		List<Ordine> ordini = ordineDAO.doRetrieveAllOrdini(0, 1000);
		
		request.setAttribute("ordini", ordini);
		request.setAttribute("numeroOrdini", ordini.size());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/adminPage/show-ordini.jsp");
        dispatcher.forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		doGet(request, response);
	}
}
