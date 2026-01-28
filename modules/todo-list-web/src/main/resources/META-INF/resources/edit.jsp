<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/init.jsp" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%-- Pega os dados que vieram da tela anterior --%>
<c:set var="tituloAtual" value="${param.titulo}" />
<c:set var="indexAtual" value="${param.tarefaIndex}" />

<%-- Cria a URL para SALVAR a edição --%>
<portlet:actionURL name="atualizarTarefa" var="atualizarURL" />

<div class="container-fluid mt-3">
    <h3>✏️ Editar Tarefa</h3>

    <form action="${atualizarURL}" method="post">
        <%-- Enviamos o ID (index) escondido para o Java saber quem alterar --%>
        <input type="hidden" name="<portlet:namespace/>tarefaIndex" value="${indexAtual}" />

        <div class="form-group">
            <label>Título da Tarefa:</label>
            <input type="text" name="<portlet:namespace/>titulo" class="form-control" value="${tituloAtual}" required />
        </div>

        <div class="btn-group mt-3">
            <button type="submit" class="btn btn-primary">💾 Salvar</button>
            <a href="<portlet:renderURL />" class="btn btn-secondary">Cancelar</a>
        </div>
    </form>
</div>