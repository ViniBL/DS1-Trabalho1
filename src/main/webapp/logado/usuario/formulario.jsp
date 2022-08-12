<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<title>Sistema de Compra/Venda de Pacotes Turísticos</title>
</head>

<body>
	<div align="center">
		<h1>Compra de Pacote Turístico</h1>
		<h2>
			<a href="pacotes">Lista de Pacotes</a>
		</h2>
	</div>
	<div align="center">
		<c:choose>
			<c:when test="${pacote != null}">
				<form action="compra" method="post">
					<%@include file="campos.jsp"%>
				</form>
			</c:when>
			<c:otherwise>
				<form action="insercao" method="post">
					<%@include file="campos.jsp"%>
				</form>
			</c:otherwise>
		</c:choose>
	</div>
	<c:if test="${!empty requestScope.mensagens}">
		<ul class="erro">
			<c:forEach items="${requestScope.mensagens}" var="mensagem">
				<li>${mensagem}</li>
			</c:forEach>
		</ul>
	</c:if>
</body>

</html>