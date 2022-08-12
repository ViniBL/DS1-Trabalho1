<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table border="1">
	<caption>
		<c:choose>
			<c:when test="${pacote != null}">
                            Edição
                        </c:when>
			<c:otherwise>
                            Cadastro
                        </c:otherwise>
		</c:choose>
	</caption>
	<c:if test="${pacotes != null}">
		
		<input type="hidden" name="id_pacote" value="${pacote.id_pacote}" />
		<input type="hidden" name="id_agencia" value="${3}" />
	</c:if>
	<tr>
		<td><label for="cidade">cidade</label></td>
		<td><input type="text" id="cidade" name="cidade" size="45" required
			value="${pacote.destino.cidade}" /></td>
	</tr>
	<tr>
		<td><label for="estado">estado</label></td>
		<td><input type="text" id="estado" name="estado" size="45" required
			value="${pacote.destino.estado}" /></td>
	</tr>
	<tr>
		<td><label for="pais">pais</label></td>
		<td><input type="text" id="pais" name="pais" size="45" required
			value="${pacote.destino.pais}" /></td>
	</tr>
	<tr>
		<td><label for="partida">Data de partida</label></td>
		<td><input type="text" id="partida" name="partida" required
			size="45" value="${pacote.data_partida}" /></td>
	</tr>
	<tr>
		<td><label for="duracao">Duração</label></td>
		<td><input type="number" id="duracao" name="duracao" size="5" required value="${pacote.duracao}" /></td>
	</tr>
	<tr>
		<td><label for="valor">Preço</label></td>
		<td><input type="number" id="valor" name="valor" required
			min="0.01" step="any" size="5" value="${pacote.valor}" /></td>
	</tr>
	<tr>
		<td><label for="descricao">Descrição</label></td>
		<td><input type="text" id="descricao" name="descricao" required
			min="0.01" step="any" size="5" value="${pacote.descricao}" /></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit" value="Salva" /></td>
	</tr>
</table>