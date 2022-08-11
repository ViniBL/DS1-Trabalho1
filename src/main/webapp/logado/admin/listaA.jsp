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
        <a href="/Login/admin/">Voltar</a>
        <div align="center">
            <h1>Agencias Cadastradas</h1>
            <h2>
                <a href="/<%=contextPath%>/admin/agencias/cadastro">Adicione Nova Agência</a>
            </h2>
        </div>

        <div align="center">
            <table border="1">
                <caption>Lista de Agências</caption>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    
                    <th>CNPJ</th>
                    <th>Descrição</th>
               
                    
                </tr>
                <c:forEach var="agencia" items="${requestScope.listaAgencias}">
                    <tr>
                        <td>${agencia.id}</td>
                        <td>${agencia.nome}</td>
                        
                        <td>${agencia.cnpj}</td>
                        <td>${agencia.descricao}</td>
                    
                        <td><a href="/<%= contextPath%>/admin/agencias/edicao?id_agencia=${agencia.id}">Edição</a>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="/<%= contextPath%>/admin/agencias/remocao?id_agencia=${agencia.id}" onclick="return confirm('Tem certeza de que deseja excluir esta agencia?');">Remoção </a></td>
                        
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