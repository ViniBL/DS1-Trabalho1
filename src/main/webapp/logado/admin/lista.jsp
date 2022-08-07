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
        <%
		    String contextPath = request.getContextPath().replace("/", "");
        %>
        <div align="center">
            <h1>Cliente Cadastrados</h1>
            <h2>
                <a href="/<%=contextPath%>/admin/clientes/cadastro">Adicione Novo Cliente</a>
            </h2>
        </div>

        <div align="center">
            <table border="1">
                <caption>Lista de Clientes</caption>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Sexo</th>
                    <th>CPF</th>
                    <th>Data de Nascimento</th>
                    <th>Telefone</th>
                    
                </tr>
                <c:forEach var="cliente" items="${requestScope.listaClientes}">
                    <tr>
                        <td>${cliente.id_cliente}</td>
                        <td>${cliente.usuario.nome}</td>
                        <td>${cliente.sexo}</td>
                        <td>${cliente.cpf}</td>
                        <td>${cliente.data_nascimento}</td>
                        <td>${cliente.telefone}</td>
                        <td><a href="/<%= contextPath%>/admin/clientes/edicao?id=${cliente.id_cliente}">Edição</a>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="/<%= contextPath%>/admin/clientes/remocao?id=${cliente.id_cliente}" onclick="return confirm('Tem certeza de que deseja excluir este item?');">Remoção </a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <ul>
            <li>
               
                <a href="${pageContext.request.contextPath}/logout.jsp">Sair</a>
            </li>
        </ul>
    </body>
</html>