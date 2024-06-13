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

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String nome = request.getParameter("name");
			String cognome = request.getParameter("surname");
			String via = request.getParameter("address");
			Integer civico = Integer.parseInt(request.getParameter("address2"));
			String citta = request.getParameter("address3");
			Integer cap = Integer.parseInt(request.getParameter("cap"));
			
			UtenteDAO utenteDAO = new UtenteDAO();
			Utente utente= new Utente();
			
			utente.setUsername(username);
			utente.setPassword(password);
			utente.setEmail(email);
			utente.setCitta(citta);
			utente.setCivico(civico);
			utente.setCap(cap);
			utente.setNome(nome);
			utente.setCognome(cognome);
			utente.setVia(via);
			
			boolean duplicate = utenteDAO.doVerifyDuplicate(utente);
			if(duplicate) {
				response.sendRedirect("http://localhost:8080/PeekABook/registration-failed.jsp");
			}
			else {
				utenteDAO.doSaveUtente(utente);
				HttpSession session = request.getSession();
				utente.setId(utenteDAO.doRetrieveIdByUsername(username));
				CarrelloDAO carrelloDAO = new CarrelloDAO();
				carrelloDAO.doCreateCarrello(utente);
				utente = utenteDAO.doRetrieveByUsernamePassword(username, password);
				synchronized (session) {
					session.setAttribute("utente", utente);
					session.setAttribute("isCommon", Boolean.TRUE);
					session.setAttribute("carrello", utente.getCarrello());
				}
			response.sendRedirect("http://localhost:8080/PeekABook/registration-success.jsp");
			}
		}
		
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		doPost(request, response);
	}
}