<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Contato</title>
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="style.css">
</head>

<body>
    <div class="content">
        <div class="box">
            <div class="box2">
                <h1>Editar Contato</h1>
                <div class="container2">
                    <form method="get" name="formContato" action="update">
                        <table>
                            <tr>
                                <td>
                                    <input class="box-form" id="form-idcon" type="text" name="idcon" readonly value="<%out.print(request.getAttribute(" idcon"));%>">
                                </td>
                            </tr>
                            <tr>
                                <td><input class="box-form" type="text" name="nome" value="<%out.print(request.getAttribute(" nome"));%>"></td>
                            </tr>
                            <tr>
                                <td><input class="box-form" type="text" name="fone" value="<%out.print(request.getAttribute(" fone"));%>"></td>
                            </tr>
                            <tr>
                                <td><input class="box-form" type="text" name="email" value="<%out.print(request.getAttribute(" email"));%>">
                                </td>
                            </tr>
                        </table>
                        <input class="button" type="button" value="Salvar" onclick="validar()">
                    </form>
                </div>
            </div>
        </div>
        <button class="btn-back" onclick="window.history.back()">
            <img src="images/icon-back.svg" alt="voltar para página anterior">
        </button>
        <footer>
            <p>Powered by: Cailo Pinheiro</p>
        </footer>
    </div>
    <script src="scripts/validador.js"></script>

</body>

</html>