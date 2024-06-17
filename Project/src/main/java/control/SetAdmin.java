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

import model.Utente;
import model.UtenteDAO;

/**
 * Servlet implementation class SetAdmin
 */
@WebServlet("/SetAdmin")
public class SetAdmin extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id= Integer.parseInt(request.getParameter("id"));
		UtenteDAO utenteDAO = new UtenteDAO();
		HttpSession session = request.getSession();
		Utente adm = null;
		adm = (Utente) session.getAttribute("utente");
		Utente u = new Utente();
		u.setId(id);
		utenteDAO.doSetAdmin(u);
		List<Utente> utenti = utenteDAO.doRetrieveAllUtentiExcAdm(adm, 0, 1000);
		request.setAttribute("utenti", utenti);
		request.setAttribute("numeroUtenti", utenti.size());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/adminPage/show-utenti.jsp");
        dispatcher.forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		doGet(request, response);
	}

}
