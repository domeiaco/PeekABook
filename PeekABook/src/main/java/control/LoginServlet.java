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

@WebServlet("/LoginServlet")

public class LoginServlet extends HttpServlet{
public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("isAdmin", Boolean.FALSE);
		session.setAttribute("isCommon", Boolean.FALSE);

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UtenteDAO utenteDAO = new UtenteDAO();
		Utente utente = utenteDAO.doRetrieveByUsernamePassword(username, password);
		if(utente==null) {
			response.sendRedirect("http://localhost:8080/PeekABook/errorlogin.html");
		}
		else {
			synchronized (session) {
				session.setAttribute("utente", utente);
				session.setAttribute("carrello", utente.getCarrello());
			}
			if(utente.getAdmin()==1) {
				session.setAttribute("isAdmin", Boolean.TRUE);
				session.setAttribute("isCommon", Boolean.FALSE);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/adminPage/admin.jsp");
				dispatcher.forward(request, response);
			}
			else {
				session.setAttribute("isAdmin", Boolean.FALSE);
				session.setAttribute("isCommon", Boolean.TRUE);
				response.sendRedirect("http://localhost:8080/PeekABook/Home");
			}
		}
	}
}