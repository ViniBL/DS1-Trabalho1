<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table border="1">
	<caption>
		<c:choose>
			<c:when test="${cliente != null}">
                            Edição
                        </c:when>
			<c:otherwise>
                            Cadastro
                        </c:otherwise>
		</c:choose>
	</caption>	
	<c:if test="${cliente != null}">
		<input type="hidden" name="id_cliente" value="${cliente.id}" />
	</c:if>
	<tr>
		<td><label for="nome">Nome</label></td>
		<td><input type="text" id="nome" name="nome" size="45"
			required value="${cliente.nome}" /></td>
	</tr>
	<tr>
		<td><label for="login">Email</label></td>
		<td><input type="text" id="login" name="login" size="45"
			required value="${cliente.login}" /></td>
	</tr>
	<tr>
		<td><label for="senha">Senha</label></td>
		<td><input type="password" id="senha" name="senha" size="45"
			required value="${cliente.senha}" /></td>
	</tr>
	<tr>
		<td><label for="cpf">CPF</label></td>
		<td><input type="text" id="cpf" name="cpf" size="45"
			required value="${cliente.cpf}" /></td>
	</tr>
	
	<tr>
		<td><label for="telefone">Telefone</label></td>
		<td><input type="text" id="telefone" name="telefone" size="45" required
			value="${cliente.telefone}" /></td>
	</tr>
	<tr>
		<td><label for="sexo">Sexo</label></td>
		<td><select id="sexo" name="sexo">
				<div var="usuario">
					<option value="M"
						${cliente.sexo==sexo.value ? 'selected' : '' }>
						M</option>
					<option value="F"
						${cliente.sexo==sexo.value ? 'selected' : '' }>
						F</option>
					<option value="N"
						${cliente.sexo==sexo.value ? 'selected' : '' }>
						N</option>
				</div>
		</select></td>
	</tr>
	<tr>
		<td><label for="data_nascimento">Data de Nascimento</label></td>
		<td><input type="text" id="data_nascimento" name="data_nascimento" required
			size="45" value="${cliente.data_nascimento}" /></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit" value="Salvar" /></td>
	</tr>
</table>