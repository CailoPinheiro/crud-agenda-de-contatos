package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {

    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "127.0.0.1:3306/dbagenda?iseTimezone=true&serverTimezone=UTC";
    String user = "root";
    String password = "dados";

    // Conecting //
    private Connection conectar() {
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            return con;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    // CRUD CREATE, READ, UPDATE and DELETE //
    // CRUD CREATE //
    public void createContato(JavaBeans contato) {
        String create = "insert into contatos (nome, fone, email) values (?,?,?)";
        try {

            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(create);
            pst.setString(1, contato.getNome());
            pst.setString(2, contato.getFone());
            pst.setString(3, contato.getEmail());
            pst.executeUpdate();
            con.close();

            /*
             * Explicação
             * 1 - PreparedStatement é uma classe modelo. Já prepareStatement é um método e
             * seu parâmetro é a variável que contém a query, nesse caso: create.
             * 2 - pst significa "prepare statmente" -> objeto da classe PreparedStatement
             * que vou usar pra chamar os métodos que preparam uma query pra ser executada
             * no banco de dados.
             * 3 - Todas as "?" vão ser substituidas pelos dados que a pessoa passar na hora
             * de criar um novo contato. Esses dados ficam armazenados nas variáveis da
             * classe Javabeans e correspondem aos itens das tabelas no banco de dados.
             */

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // CRUD READ // vai ser responsável por pegar todos os dados do banco de dados e adicionar dentro do vetor dinâmico que é referenciado pela classe JavaBeans
    public ArrayList<JavaBeans> obterContatos() {
        // Criando objeto para acessar a classe JavaBeans //
        ArrayList<JavaBeans> contatos = new ArrayList<>();  // recebe de forma dinâmica, os dados do banco de dados.

        String read = "select * from contatos order by nome";

        try {
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(read);
            ResultSet resultContatos = pst.executeQuery();

            // Repete enquanto houver contatos //
            while (resultContatos.next()) {

                // vars de apoio que recebem os dados do banco
                String idcon = resultContatos.getString(1);
                String nome = resultContatos.getString(2);
                String fone = resultContatos.getString(3);
                String email = resultContatos.getString(4);
                // armazenando tudo do banco dentro do vetor dinâmico (populando o arraylist) nas variáveis JavaBeans//
                contatos.add(new JavaBeans(idcon, nome, fone, email));
            }

            con.close();
            return contatos;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    // CRUD UPDATE //
    // Método responsável por selecionar o contato via ID //
    public void selectContato(JavaBeans contato) {

        String read2 = "select * from contatos where idcon = ?";
        try {
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(read2);
            pst.setString(1, contato.getIdcon());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                // setando as variáveis JavaBeans //
                contato.setIdcon(rs.getString(1));
                contato.setNome(rs.getString(2));
                contato.setFone(rs.getString(3));
                contato.setEmail(rs.getString(4));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Método responsável por editar o contato no banco de dados //
    public void alterarContato(JavaBeans contato) { //
        String create = "update contatos set nome=?, fone=?, email=? where idcon=?";
        try {
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(create);
            pst.setString(1, contato.getNome());
            pst.setString(2, contato.getFone());
            pst.setString(3, contato.getEmail());
            pst.setString(4, contato.getIdcon());
            pst.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // CRUD DELETE //
    public void excluirContato(JavaBeans contato) {
        String delete = "delete from contatos where idcon=?";

        try {
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(delete);
            pst.setString(1, contato.getIdcon());
            pst.executeUpdate();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
