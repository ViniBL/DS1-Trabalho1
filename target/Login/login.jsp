<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Autenticação de Usuário</title>
    </head>
    <body>
        <h1>Autenticação de Usuário</h1>
        <c:if test="${mensagens.existeErros}">
            <div id="erro">
                <ul>
                    <c:forEach var="erro" items="${mensagens.erros}">
                        <li> ${erro} </li>
                        </c:forEach>
                </ul>
            </div>
        </c:if>
        <form method="post" action="index.jsp">
            <table>
                <tr>
                    <th>Login: </th>
                    <td><input type="text" name="login"
                               value="${param.login}"/></td>
                </tr>
                <tr>
                    <th>Senha: </th>
                    <td><input type="password" name="senha" /></td>
                </tr>
                <tr>
                    <td colspan="2"> 
                        <input type="submit" name="bOK" value="Entrar"/>
                    </td>
                </tr>
            </table>
        <%
		    String contextPath = request.getContextPath().replace("/", "");
	    %>
	    <div align="center">
		    <h1>Pacotes de Viagens Disponíveis</h1>
            <h2>
			    <a href="/<%=contextPath%>">Menu Principal</a> &nbsp;&nbsp;&nbsp; <a
				    href="/<%=contextPath%>/livros/cadastro">Adicione Novo Livro</a>
		    </h2>
	    </div>

	    <div align="center">
		    <table border="1">
			    <caption>Lista de Pacotes</caption>
			    <tr>
                    <th>Nome da Agência</th>
                    <th>Destino</th>
                    <th>Data de Partida</th>
                    <th>Duração da Viagem</th>
                    <th>Valor</th>
                    <th>Descrição</th>
                </tr>
                <c:forEach var="pacote" items="${requestScope.listaPacotes}">
                    <tr>
                        <td>${pacote.agencia.nome}</td>
                        <td>${pacote.destino.cidade} - ${pacote.destino.estado}/${pacote.destino.pais}</td>
                        <td>${pacote.data_partida}</td>
                        <td>${pacote.duracao} dias</td>
                        <td>R$${pacote.valor}</td>
                        <td>${pacote.descricao}</td>
                        <td><a href="/<%= contextPath%>/livros/edicao?id=${livro.id}">Edição</a>
                            &nbsp;&nbsp;&nbsp;&nbsp; <a
                            href="/<%= contextPath%>/livros/remocao?id=${livro.id}"
                            onclick="return confirm('Tem certeza de que deseja excluir este item?');">
                                Remoção </a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>