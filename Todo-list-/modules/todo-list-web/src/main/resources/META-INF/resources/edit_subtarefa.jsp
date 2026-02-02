

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/init.jsp" %>
<%@ page import="gui.todolist.service.TarefaService" %>
<%@ page import="gui.todolist.model.SubTarefa" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>

<%
    long tarefaId = ParamUtil.getLong(renderRequest, "tarefaId");
    long subId = ParamUtil.getLong(renderRequest, "subId");

    SubTarefa subParaEditar = TarefaService.getInstance().getSubTarefa(tarefaId, subId);
%>

<div class="container-fluid mt-4">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-sm">
                <div class="card-header bg-white">
                    <h5 class="m-0">Editar Item</h5>
                </div>
                <div class="card-body">

                    <% if (subParaEditar != null) { %>
                        <portlet:actionURL name="atualizarSubTarefa" var="atualizarURL">
                            <portlet:param name="tarefaId" value="<%= String.valueOf(tarefaId) %>" />
                            <portlet:param name="subId" value="<%= String.valueOf(subId) %>" />
                        </portlet:actionURL>

                        <form action="${atualizarURL}" method="post">
                            <div class="form-group">
                                <label>Descrição:</label>
                                <input type="text" name="<portlet:namespace/>descricao"
                                       class="form-control"
                                       value="<%= subParaEditar.getDescricao() %>"
                                       autofocus required />
                            </div>

                            <div class="mt-3 text-right">
                                <portlet:renderURL var="cancelarURL">
                                    <portlet:param name="mvcPath" value="/subtarefas.jsp" />
                                    <portlet:param name="tarefaId" value="<%= String.valueOf(tarefaId) %>" />
                                </portlet:renderURL>
                                <a href="${cancelarURL}" class="btn btn-secondary mr-1">Cancelar</a>

                                <button type="submit" class="btn btn-primary">Salvar Alterações</button>
                            </div>
                        </form>
                    <% } else { %>
                        <div class="alert alert-danger">
                            Erro: Subtarefa não encontrada.
                            <portlet:renderURL var="voltarErroURL">
                                <portlet:param name="mvcPath" value="/subtarefas.jsp" />
                                <portlet:param name="tarefaId" value="<%= String.valueOf(tarefaId) %>" />
                            </portlet:renderURL>
                            <a href="${voltarErroURL}">Voltar</a>
                        </div>
                    <% } %>

                </div>
            </div>
        </div>
    </div>
</div>