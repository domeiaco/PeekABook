package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;
import model.*;


@WebServlet("/ShowProduct")
public class ShowProduct extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		ArticoloDAO articoloDAO = new ArticoloDAO();
		LibroDAO libroDAO = new LibroDAO();
		ArrayList<Articolo> articoli = articoloDAO.doRetrieveArticoliByNome(nome, 0, 1);
		Articolo a = articoli.get(0);
		Libro l = libroDAO.doRetrieveLibroByArticolo(a.getCodice());
		
		AutoreDAO autoreDAO = new AutoreDAO();
		Autore autore = autoreDAO.doRetrieveAutoreByLibro(l);
		l.setAutore(autore);
		
		request.setAttribute("articolo", l);
		
		GenereDAO genereDAO = new GenereDAO();
		Genere genere = genereDAO.doRetrieveGenereByLibro(l);
		ArrayList<Libro> libri = libroDAO.doRetrieveOtherLibriByGenere(genere, l, 0, 4);
		ArrayList<Articolo> consigliati = new ArrayList<>();
		for(Libro lib : libri) {
			consigliati.add(lib);
		}
		
		request.setAttribute("consigliati", consigliati);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("show-product.jsp");
        dispatcher.forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}
