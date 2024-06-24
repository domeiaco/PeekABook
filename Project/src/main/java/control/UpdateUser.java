package control;

import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		try {
			String email = request.getParameter("email");
			String nome = request.getParameter("name");
			String cognome = request.getParameter("surname");
			String via = request.getParameter("address");
			Integer civico = Integer.parseInt(request.getParameter("address2"));
			String citta = request.getParameter("address3");
			Integer cap = Integer.parseInt(request.getParameter("cap"));
			
			UtenteDAO utenteDAO = new UtenteDAO();
			Utente utente= null;
			HttpSession session = request.getSession();
			synchronized (session) {
				utente = (Utente) session.getAttribute("utente");
				utente.setEmail(email);
				utente.setCitta(citta);
				utente.setCivico(civico);
				utente.setCap(cap);
				utente.setNome(nome);
				utente.setCognome(cognome);
				utente.setVia(via);
				session.removeAttribute("utente");
				session.setAttribute("utente", utente);
				utenteDAO.doUpdateUtente(utente);
			}
			
			
			
			response.sendRedirect("http://localhost:8080/PeekABook/show-info.jsp");
		}
		
		catch(Exception e) {
			response.sendRedirect("http://localhost:8080/PeekABook/show-info.jsp");
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		doPost(request, response);
	}
}