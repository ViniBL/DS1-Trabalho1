<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
    <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">-->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Autenticação de Usuário</title>
    </head>
    <body>
        <%
		    String contextPath = request.getContextPath().replace("/", "");
	    %>
        <a href="/Login">Login</a>
	    <div align="center">
		    <h1>Pacotes de Viagens Disponíveis 2.1</h1>
           
	    </div>

        <div>
            <form action="/Login/pacote/lista.jsp" method="POST">
                <label>Filtrar por: </label> <br/>
                <!--<select id="filter" name="filtro">
                    <option value="destino">Destino (Ex.: 'Cidade/Estado/Pais')</option>
                    <option value="agencia">Nome da Agência</option>
                    <option value="data_partida">Data de Partida (Ex.: 'DD-MM-YYYY')</option>
                </select>-->
                
                
                    <label>Cidade</label>
                    <input type="text" maxlength=50 name="cidade"> <br/>
                    <label>Estado</label>
                    <input type="text" maxlength=50 name="estado"> <br/>
                    <label>Pais</label>
                    <input type="text" maxlength=50 name="pais"> <br/>
                
               
                    <label>Nome da Agência</label>
                    <input type="text" maxlength=50 name="nome"> <br/>
               
               
                    <label>Data (DD-MM-YYYY)</label>
                    <input type="text" maxlength=50 name="data_partida"> <br/>
               
                <input type="submit" value="Buscar">
            </form>
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
                        <!--<td>${usuario.id}</td>
                        <td>${usuario.nome}</td>
                        <td>${usuario.login}</td>
                        <td>${usuario.senha}</td>
                        <td>${usuario.papel}</td>-->
                        
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>