<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/init.jsp" %>
<%@ page import="gui.todolist.service.TarefaService" %>
<%@ page import="gui.todolist.model.SubTarefa" %>

<%
    long tarefaId = ParamUtil.getLong(renderRequest, "tarefaId");
    long subId = ParamUtil.getLong(renderRequest, "subId");
    SubTarefa sub = TarefaService.getInstance().getSubTarefa(tarefaId, subId);
%>

<div class="container mt-4">
    <div class="card shadow-sm border-0 col-md-6 mx-auto">
        <div class="card-header bg-white py-3">
            <h5 class="m-0">✏️ Editar Item</h5>
        </div>
        <div class="card-body">
            <% if (sub != null) { %>
                <portlet:actionURL name="atualizarSubTarefa" var="updateSubURL">
                    <portlet:param name="tarefaId" value="<%= String.valueOf(tarefaId) %>" />
                    <portlet:param name="subId" value="<%= String.valueOf(subId) %>" />
                </portlet:actionURL>
                <form action="${updateSubURL}" method="post">
                    <div class="form-group">
                        <label>Descrição do Item:</label>
                        <input type="text" name="<portlet:namespace/>descricao" class="form-control" value="<%= sub.getDescricao() %>" required />
                    </div>
                    <div class="mt-4 d-flex justify-content-end">
                        <portlet:renderURL var="cancelURL">
                            <portlet:param name="mvcPath" value="/subtarefas.jsp" />
                            <portlet:param name="tarefaId" value="<%= String.valueOf(tarefaId) %>" />
                        </portlet:renderURL>
                        <a href="${cancelURL}" class="btn btn-outline-secondary mr-2">Cancelar</a>
                        <button type="submit" class="btn btn-primary">Salvar</button>
                    </div>
                </form>
            <% } else { %>
                <div class="alert alert-danger text-center">
                    <strong>Erro:</strong> Item não encontrado. <br>
                    <small>Crie uma tarefa nova após o reinício do servidor.</small>
                </div>
            <% } %>
        </div>
    </div>
</div>