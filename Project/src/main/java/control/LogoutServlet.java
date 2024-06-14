package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Carrello;
import model.CarrelloDAO;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		synchronized (session){
			CarrelloDAO carrelloDAO = new CarrelloDAO(); 
			Carrello c = (Carrello) session.getAttribute("carrello");
			carrelloDAO.doUpdateCarrello(c);
			session.invalidate();
		}
		
		response.sendRedirect("http://localhost:8080/PeekABook/Home");
	}

}
