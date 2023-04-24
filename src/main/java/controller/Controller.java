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

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete" })
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
            listarContatos(request, response);

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

    // LISTAR CONTATOS //
    protected void listarContatos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Criando um ojeto que vai receber os dados da classe JavaBeans
        ArrayList<JavaBeans> listaDeContatos = dao.obterContatos(); // quando executar esse método (obterContatos), o resultado é armazenado dentro do objeto listaDeContatos
        /*
         * Explicação: o objeto listaDeContatos vai ser usado pra trazer os dados do
         * vetor dinâmico que tem lá dentro da classe DAO. O método que retorna esse
         * vetor dinâmico é desenvolvido lá na própria classe DAO. É feito assim pra
         * melhorar a segurança do projeto e não deixar uma conexão direta com o banco
         * de dados.
         */
        // Encaminhar a lista ao documento agenda.jsp
        request.setAttribute("contatos", listaDeContatos);
        RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
        rd.forward(request, response);
    }

    // NOVO CONTATO //
    protected void addContato(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        contato.setNome(request.getParameter("nome"));
        contato.setFone(request.getParameter("fone"));
        contato.setEmail(request.getParameter("email"));
        // invocar o método createContato passando o objeto contato //
        dao.createContato(contato);
        // redirecionando para o documento agenda.jsp //
        response.sendRedirect("main");
    }

    // EDITAR CONTATO //
    /*Método responsável por permitir a edição de um contato existente. Ele recebe
    uma requisição HTTP contendo o ID do contato que será editado e, em seguida,
    seta o ID no objeto JavaBeans correspondente.*/

    protected void prepareEditContato(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // recebimento do id do contato que será editado //
        String idcon = request.getParameter("idcon");
        // setar a variável JavaBeans
        contato.setIdcon(idcon);
        // Executar o método selecionar contato
        dao.selectContato(contato);
        // Setar os atributos do form com o conteúdo da classe JavaBeans
        request.setAttribute("idcon", contato.getIdcon());
        request.setAttribute("nome", contato.getNome());
        request.setAttribute("fone", contato.getFone());
        request.setAttribute("email", contato.getEmail());
        // encaminha ao documento editar.jsp
        RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
        rd.forward(request, response);
    }

    protected void editContato(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Setar os atributos nas variáveis JavaBeans
        contato.setIdcon(request.getParameter("idcon"));
        contato.setNome(request.getParameter("nome"));
        contato.setFone(request.getParameter("fone"));
        contato.setEmail(request.getParameter("email"));
        // executar o método alterar contato da classe DAO
        dao.alterarContato(contato);
        // redirect to agenda.jsp com todas as alterações
        response.sendRedirect("main");
    }

    // EXCLUIR CONTATO
    private void deleteContato(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recebimento do id do contato a ser excluído (validador.js)
        String idcon = request.getParameter("idcon");
        // Setar a variável idcon na classe JavaBeans
        contato.setIdcon(idcon);
        // Executar o método excluirContato() da classe DAO passando o objeto contato
        dao.excluirContato(contato);
        // redirect to agenda.jsp com todas as alterações
        response.sendRedirect("main");
    }
}
