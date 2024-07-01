package control;

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

import model.Ordine;
import model.OrdineDAO;
import model.UtenteDAO;
import model.Utente;


@WebServlet("/ShowOrdiniPersonali")
public class ShowOrdiniPersonali extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utente u = null;
		synchronized (session) {
			u= (Utente) session.getAttribute("utente");
		}
		OrdineDAO ordineDAO = new OrdineDAO();
		List<Ordine> ordini = ordineDAO.doRetrieveOrdiniByUtente(u);
		request.setAttribute("ordini", ordini);
		request.setAttribute("numeroOrdini", ordini.size());

        RequestDispatcher dispatcher = request.getRequestDispatcher("show-ordini-utente.jsp");
        dispatcher.forward(request, response);
        
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
