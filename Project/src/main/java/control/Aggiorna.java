package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Libro;
import model.LibroDAO;

import java.util.*;

@WebServlet("/Aggiorna")
public class Aggiorna extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer codice = Integer.parseInt(request.getParameter("codice"));
		LibroDAO libroDAO = new LibroDAO();
		Libro libro = libroDAO.doRetrieveLibroByArticolo(codice);
		libro.setAnno(Integer.parseInt(request.getParameter("year")));
		libro.setDescrizione(request.getParameter("description"));
		libro.setEditore(request.getParameter("editor"));
		libro.setPagine(Integer.parseInt(request.getParameter("pages")));
		libro.setPrezzo(Double.parseDouble(request.getParameter("price")));
		libro.setQuantita(libro.getQuantita()+Integer.parseInt(request.getParameter("quantity")));
		libro.setValutazione(Integer.parseInt(request.getParameter("rating")));

		
		libroDAO.doUpdateLibro(libro);
		
		 List<Libro> articoli = libroDAO.doRetrieveLibro(0, 1000);
		 request.setAttribute("articoli", articoli);
	     RequestDispatcher dispatcher = request.getRequestDispatcher("adminPage/show-articoli.jsp");
	     dispatcher.forward(request, response);
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
