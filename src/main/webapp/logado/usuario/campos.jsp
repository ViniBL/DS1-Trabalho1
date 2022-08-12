<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<table border="1">
	<caption>
		Proposta de Compra
	</caption>
	<c:if test="${pacote != null}">
		<input type="hidden" name="id_pacote" value="${pacote.id_pacote}" />
	</c:if>
	<tr>
		<th><label for="valor">Valor do Pacote</label></th>
		<th>Valor da Proposta</th>
	</tr>
	<tr>
		<td><input type="number" id="valor" name="valor" size="10"
			required value="${pacote.valor}" /></td>
		<td><input type="number" id="valorProposta" name="valorPorposta" size="10" /></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit" value="Comprar" /></td>
	</tr>
</table>

