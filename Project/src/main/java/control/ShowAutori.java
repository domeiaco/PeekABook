package control;

import model.Autore;
import model.AutoreDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ShowAutori")
public class ShowAutori extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        AutoreDAO autoreDAO= new AutoreDAO();
        List<Autore> autori = autoreDAO.doRetrieveAutori(0, 1000);
        request.setAttribute("autori", autori);
        RequestDispatcher dispatcher = request.getRequestDispatcher("adminPage/show-autori.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        doGet(request, response);
    }
}
