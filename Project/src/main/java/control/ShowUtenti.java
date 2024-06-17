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

@WebServlet("/ShowUtenti")

public class ShowUtenti extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtenteDAO utenteDAO = new UtenteDAO();
		HttpSession session = request.getSession();
		Utente adm = null;
		adm = (Utente) session.getAttribute("utente");
		List<Utente> utenti = utenteDAO.doRetrieveAllUtentiExcAdm(adm, 0, 1000);
		
		request.setAttribute("utenti", utenti);
		request.setAttribute("numeroUtenti", utenti.size());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/adminPage/show-utenti.jsp");
        dispatcher.forward(request, response);
	}
}