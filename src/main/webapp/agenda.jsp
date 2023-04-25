<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
  @ SuppressWarnings ("unchecked")
	ArrayList<JavaBeans> listaDeContatos = (ArrayList<JavaBeans>) request.getAttribute("contatos");
%>

<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agenda de Contatos</title>
    <link rel="icon" href="images/favicon.ico">
    <link rel="stylesheet" href="style.css">
</head>

<body>
  <div class="content">
    <div class="box">
      <div class="content-box">
        <h1>Agenda de Contatos</h1>

        <table id="table-contatos">
          <thead>
            <tr>
              <th>Id</th>
              <th>Nome</th>
              <th>Telefone</th>
              <th>E-mail</th>
              <th>Opções</th>
            </tr>
          </thead>
          <tbody>
            <% for (int i = 0; i < listaDeContatos.size(); i++) { %>
              <tr>
                <td><%=listaDeContatos.get(i).getIdcon()%></td>
                <td><%=listaDeContatos.get(i).getNome()%></td>
                <td><%=listaDeContatos.get(i).getFone()%></td>
                <td><%=listaDeContatos.get(i).getEmail()%></td>
                <td class="flex">
                  <a href="select?idcon=<%=listaDeContatos.get(i).getIdcon()%>" class="edit-del">
                    <img src="images/icon-edit.svg" alt="ícone para editar contato">
                  </a>
                  <a href="javascript: confirmarExclusao(<%=listaDeContatos.get(i).getIdcon()%>)" class="edit-del">
                    <img src="images/icon-trash.svg" alt="ícone para apagar contato">
                  </a>
                </td>
              </tr>
              <%}%>
          </tbody>
        </table>

      </div>
      <a class="button" href="novo.html">Novo Contato</a>
    </div>

    <button class="btn-back" onclick="window.history.back()">
      <img src="images/icon-back.svg" alt="voltar para página anterior">
    </button>
    
    <footer>
      <p>Powered by: Cailo Pinheiro</p>
    </footer>
  </div>
  <script src="scripts/confirm-delete.js"></script>
</body>
</html>