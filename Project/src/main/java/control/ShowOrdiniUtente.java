package control;

import model.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ShowOrdiniUtente")

public class ShowOrdiniUtente extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		OrdineDAO ordineDAO = new OrdineDAO();
		Integer id = Integer.parseInt(request.getParameter("utente"));
		UtenteDAO utenteDAO = new UtenteDAO();
		Utente u = utenteDAO.doRetrieveById(id);
		List<Ordine> ordini = ordineDAO.doRetrieveOrdiniByUtente(u);
		
		request.setAttribute("ordini", ordini);
		request.setAttribute("numeroOrdini", ordini.size());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/adminPage/show-ordini.jsp");
        dispatcher.forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        doGet(request, response);
    }
}
