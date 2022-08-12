<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu do Sistema</title>
    </head>
    <body>
        <h1>Página do Usuário</h1>
        <p>Olá ${sessionScope.usuarioLogado.nome}</p>
        <br>
        <a href="pacotes">Lista Pacotes</a>
        <br>
        <a href="pacotesAdquiridos">Pacotes Adquiridos</a>
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/logout.jsp">Sair</a>
            </li>
        </ul>
    </body>
</html>