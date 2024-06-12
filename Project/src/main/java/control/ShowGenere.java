package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Genere;
import model.GenereDAO;
import model.Libro;
import model.LibroDAO;

/**
 * Servlet implementation class ShowGenere
 */
@WebServlet("/ShowGenere")
public class ShowGenere extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String genere = request.getParameter("genere");
		GenereDAO genereDAO = new GenereDAO();
		Genere g = genereDAO.doRetrieveGenereByNome(genere);
		LibroDAO libroDAO = new LibroDAO();
		List<Libro> libri = libroDAO.doRetrieveLibroByGenere(g, 0, 100);
		request.setAttribute("articoli", libri);
		request.setAttribute("genere", g);
		RequestDispatcher dispatcher = request.getRequestDispatcher("show-genere.jsp");
        dispatcher.forward(request, response);
	}

}
