<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/init.jsp" %>
<%@ page import="gui.todolist.service.TarefaService" %>
<%@ page import="gui.todolist.model.SubTarefa" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>

<%
    long tarefaId = ParamUtil.getLong(renderRequest, "tarefaId");
    long subId = ParamUtil.getLong(renderRequest, "subId");
    SubTarefa sub = TarefaService.getInstance().getSubTarefa(tarefaId, subId);
%>

<div class="container mt-4">
    <div class="card shadow-sm border-0 col-md-6 mx-auto">
        <div class="card-header bg-white py-3">
            <h5 class="m-0" style="font-weight: bold;">✏️ Editar Item</h5>
        </div>
        <div class="card-body">
            <% if (sub != null) { %>
                <portlet:actionURL name="atualizarSubTarefa" var="updateSubURL">
                    <portlet:param name="tarefaId" value="<%= String.valueOf(tarefaId) %>" />
                    <portlet:param name="subId" value="<%= String.valueOf(subId) %>" />
                </portlet:actionURL>

                <form action="${updateSubURL}" method="post">
                    <div class="form-group">
                        <label style="font-weight: 600;">Nova Descri&ccedil;&atilde;o do Item:</label>
                        <input type="text" name="<portlet:namespace/>descricao"
                               class="form-control"
                               value="<%= sub.getDescricao() %>"
                               required maxlength="100" />
                    </div>
                    <div class="mt-4 d-flex justify-content-end">
                        <portlet:renderURL var="cancelURL">
                            <portlet:param name="mvcPath" value="/subtarefas.jsp" />
                            <portlet:param name="tarefaId" value="<%= String.valueOf(tarefaId) %>" />
                        </portlet:renderURL>
                        <a href="${cancelURL}" class="btn btn-outline-secondary mr-2">Cancelar</a>
                        <button type="submit" class="btn btn-primary">Salvar Alterações</button>
                    </div>
                </form>
            <% } else { %>
                <div class="alert alert-danger">Erro: Item n&atilde;o encontrado.</div>
            <% } %>
        </div>
    </div>
</div>