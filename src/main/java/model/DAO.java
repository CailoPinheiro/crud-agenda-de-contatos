package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {

    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
    String user = "root";
    String password = "dados";

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
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList<JavaBeans> obterContatos() {
        ArrayList<JavaBeans> contatos = new ArrayList<>();
        String read = "select * from contatos order by nome";

        try {
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(read);
            ResultSet resultContatos = pst.executeQuery();
            while (resultContatos.next()) {
                String idcon = resultContatos.getString(1);
                String nome = resultContatos.getString(2);
                String fone = resultContatos.getString(3);
                String email = resultContatos.getString(4);
                contatos.add(new JavaBeans(idcon, nome, fone, email));
            }
            con.close();
            return contatos;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public void selectContato(JavaBeans contato) {
        String read2 = "select * from contatos where idcon = ?";

        try {
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(read2);
            pst.setString(1, contato.getIdcon());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
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

    public void updateContato(JavaBeans contato) { //
        String update = "update contatos set nome=?, fone=?, email=? where idcon=?";

        try {
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(update);
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
