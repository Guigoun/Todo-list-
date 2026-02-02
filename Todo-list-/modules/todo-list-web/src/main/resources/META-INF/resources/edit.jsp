<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/init.jsp" %>
<%@ page import="gui.todolist.service.TarefaService" %>
<%@ page import="gui.todolist.model.Tarefa" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>

<%
    long tarefaId = ParamUtil.getLong(renderRequest, "tarefaId");
    Tarefa tarefaParaEditar = TarefaService.getInstance().getTarefa(tarefaId);
%>

<div class="container mt-4">
    <div class="card shadow-sm border-0 col-md-6 mx-auto">
        <div class="card-header bg-white py-3">
            <h5 class="m-0" style="font-weight: bold;">✏️ Editar Tarefa</h5>
        </div>
        <div class="card-body">
            <% if (tarefaParaEditar != null) { %>
                <portlet:actionURL name="atualizarTarefa" var="updateURL" />
                <form action="${updateURL}" method="post">
                    <input type="hidden" name="<portlet:namespace/>tarefaId" value="<%= tarefaId %>" />
                    <div class="form-group">
                        <label style="font-weight: 600;">Nova Descri&ccedil;&atilde;o:</label>
                        <%-- Validação: 'required' impede envio vazio --%>
                        <input type="text" name="<portlet:namespace/>descricao"
                               class="form-control"
                               value="<%= tarefaParaEditar.getDescricao() %>"
                               required maxlength="100" />
                    </div>
                    <div class="mt-4 d-flex justify-content-end">
                        <portlet:renderURL var="cancelURL"><portlet:param name="mvcPath" value="/view.jsp" /></portlet:renderURL>
                        <a href="${cancelURL}" class="btn btn-outline-secondary mr-2">Cancelar</a>
                        <button type="submit" class="btn btn-primary">Salvar Alterações</button>
                    </div>
                </form>
            <% } else { %>
                <div class="alert alert-danger">Erro: Tarefa n&atilde;o encontrada.</div>
            <% } %>
        </div>
    </div>
</div>