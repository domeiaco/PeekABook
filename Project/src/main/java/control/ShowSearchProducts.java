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

import model.Articolo;
import model.ArticoloDAO;


@WebServlet("/ShowSearchProducts")
public class ShowSearchProducts extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome= request.getParameter("nome");
		ArticoloDAO articoloDAO = new ArticoloDAO();
		List<Articolo> articoli = articoloDAO.doRetrieveArticoliByNomeLike(nome, 0, 20);
		
		if(articoli.isEmpty()) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("no-articles.jsp");
	        dispatcher.forward(request, response);
		}
		
		else {
			request.setAttribute("articoli", articoli);
			RequestDispatcher dispatcher = request.getRequestDispatcher("show-all.jsp");
        	dispatcher.forward(request, response);
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
