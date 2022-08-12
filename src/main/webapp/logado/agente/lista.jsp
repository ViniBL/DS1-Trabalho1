<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
    <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">-->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Autenticação de Agência</title>
    </head>
    <body>
        <%
		    String contextPath = request.getContextPath().replace("/", "");
	    %>
        <a href="/Login/agente/">Voltar</a>
	    <div align="center">
		    <h1>Pacotes de Viagens</h1>
           
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
                        <td>${pacote.usuario.nome}</td>
                        <td>${pacote.destino.cidade} - ${pacote.destino.estado}/${pacote.destino.pais}</td>
                        <td>${pacote.data_partida}</td>
                        <td>${pacote.duracao} dias</td>
                        <td>R$${pacote.valor}</td>
                        <td>${pacote.descricao}</td>
                        
                            <td>
                                <a href="/<%= contextPath%>/agente/pacotes/edicao?id_pacote=${pacote.id_pacote}">Edição</a>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <a href="/<%= contextPath%>/agente/pacotes/remocao?id_pacote=${pacote.id_pacote}" onclick="return confirm('Tem certeza de que deseja excluir este item?');">Remoção</a>
                            </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>