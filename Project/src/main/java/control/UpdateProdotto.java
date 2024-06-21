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

@WebServlet("/UpdateProdotto")

public class UpdateProdotto extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Integer codice = Integer.parseInt(request.getParameter("codice"));
        LibroDAO libroDAO = new LibroDAO();
        Libro a= libroDAO.doRetrieveLibroByArticolo(codice);
        request.setAttribute("articolo", a);
        RequestDispatcher dispatcher= request.getRequestDispatcher("adminPage/update-prodotto.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        doGet(request, response);
    }
}