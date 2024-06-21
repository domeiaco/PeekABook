package control;

import model.Articolo;
import model.ArticoloDAO;
import model.Libro;
import model.LibroDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ModificaProdotti")

public class ModificaProdotti extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LibroDAO libroDAO = new LibroDAO();
        List<Libro> articoli = libroDAO.doRetrieveLibro(0, 1000);
        request.setAttribute("articoli", articoli);
        RequestDispatcher dispatcher = request.getRequestDispatcher("adminPage/show-articoli.jsp");
        dispatcher.forward(request, response);
    
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        doGet(request, response);
    }
}