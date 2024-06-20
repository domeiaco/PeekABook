package control;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/InsertProdotto")
public class InsertProdotto extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String nome =  request.getParameter("name");
		Integer quantita= Integer.parseInt(request.getParameter("quantity"));
        Double prezzo= Double.parseDouble(request.getParameter("price"));
        String descrizione= request.getParameter("description");
        Integer anno= Integer.parseInt(request.getParameter("year"));
        Long isbn = Long.parseLong(request.getParameter("isbn"));
        Integer pagine = Integer.parseInt(request.getParameter("pages"));
        String editore = request.getParameter("editor");
        Integer codAutore = Integer.parseInt(request.getParameter("autore"));
        String gen = request.getParameter("genere");
        Integer rating = Integer.parseInt(request.getParameter("rating"));
        String imm = request.getParameter("imm");
       
		Autore autore = new Autore();
		autore.setCodice(codAutore);

		Genere genere = new Genere();
		genere.setNome(gen);
		
		Libro libro = new Libro();
		
		libro.setNome(nome);
		libro.setAnno(anno);
		libro.setAutore(autore);
		libro.setDescrizione(descrizione);
		libro.setEditore(editore);
		libro.setGenere(genere);
		libro.setPagine(pagine);
		libro.setIsbn(isbn);
		libro.setPrezzo(prezzo);
		libro.setQuantita(quantita);
		libro.setValutazione(rating);
		libro.setTitolo(nome);
		libro.setPathImg(imm);
		System.out.println(imm);
		
		LibroDAO libroDAO= new LibroDAO();
        libroDAO.doSaveLibro(libro);
        libroDAO.doSetGenereLibro(libro);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ShowAll");
		dispatcher.forward(request, response);
    }
}
