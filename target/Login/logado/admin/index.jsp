<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu do Sistema</title>
    </head>
    <body>
        <h1>Página do Administrador</h1>
        
        <p>Olá ${sessionScope.usuarioLogado.nome}</p>
        <a href="clientes">Lista de Clientes</a>
        <a href="agencias">Lista de Agencias</a>
        <ul>
            <li>
               
                <a href="${pageContext.request.contextPath}/logout.jsp">Sair</a>
            </li>
        </ul>
    </body>
</html>