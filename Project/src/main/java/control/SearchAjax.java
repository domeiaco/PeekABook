package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Articolo;
import model.ArticoloDAO;

@WebServlet("/SearchAjax")
public class SearchAjax extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticoloDAO articoloDAO = new ArticoloDAO();
		List<Articolo> articoli = articoloDAO.doRetrieveArticoli(0, 1000);
		ArrayList<String> nomi = new ArrayList<>();
		for(Articolo a: articoli) {
			nomi.add(a.getNome());
		}
		Gson parser = new Gson();
		response.getWriter().write(parser.toJson(nomi));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticoloDAO articoloDAO = new ArticoloDAO();
		List<Articolo> articoli = articoloDAO.doRetrieveArticoli(0, 1000);
		ArrayList<String> nomi = new ArrayList<>();
		for(Articolo a: articoli) {
			nomi.add(a.getNome());
		}
		Gson parser = new Gson();
		response.getWriter().write(parser.toJson(nomi));
	}

}
