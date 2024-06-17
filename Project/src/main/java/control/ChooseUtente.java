package control;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Utente;
import model.UtenteDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ChooseUtente")
public class ChooseUtente extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		UtenteDAO utenteDAO = new UtenteDAO();
		HttpSession session = request.getSession();
		Utente adm = (Utente) (session.getAttribute("utente"));
		List<Utente> utenti = utenteDAO.doRetrieveAllUtenti(0,1000);
		request.setAttribute("utenti", utenti);
		RequestDispatcher dispatcher = request.getRequestDispatcher("adminPage/choose-utente.jsp");
        dispatcher.forward(request, response);
    }
}
