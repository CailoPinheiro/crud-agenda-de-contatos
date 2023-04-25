package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = {"/Controller", "/main", "/insert", "/select", "/update", "/delete"})
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;
    DAO dao = new DAO();
    JavaBeans contato = new JavaBeans();

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        if (action.equals("/main")) {
            listContatos(request, response);
        } else if (action.equals("/insert")) {
            addContato(request, response);
        } else if (action.equals("/select")) {
            prepareEditContato(request, response);
        } else if (action.equals("/update")) {
            editContato(request, response);
        } else if (action.equals("/delete")) {
            deleteContato(request, response);
        } else {
            response.sendRedirect("main");
        }
    }

    protected void listContatos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<JavaBeans> listaDeContatos = dao.obterContatos();
        request.setAttribute("contatos", listaDeContatos);
        RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
        rd.forward(request, response);
    }

    protected void addContato(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        contato.setNome(request.getParameter("nome"));
        contato.setFone(request.getParameter("fone"));
        contato.setEmail(request.getParameter("email"));
        dao.createContato(contato);
        response.sendRedirect("main");
    }

    protected void prepareEditContato(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        contato.setIdcon(request.getParameter("idcon"));
        dao.selectContato(contato);
        request.setAttribute("idcon", contato.getIdcon());
        request.setAttribute("nome", contato.getNome());
        request.setAttribute("fone", contato.getFone());
        request.setAttribute("email", contato.getEmail());
        RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
        rd.forward(request, response);
    }

    protected void editContato(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        contato.setIdcon(request.getParameter("idcon"));
        contato.setNome(request.getParameter("nome"));
        contato.setFone(request.getParameter("fone"));
        contato.setEmail(request.getParameter("email"));
        dao.updateContato(contato);
        response.sendRedirect("main");
    }

    private void deleteContato(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        contato.setIdcon(request.getParameter("idcon"));
        dao.excluirContato(contato);
        response.sendRedirect("main");
    }
}
