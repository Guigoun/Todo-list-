<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/init.jsp" %>

<%-- Imports necessários para lógica, busca de parâmetros e segurança --%>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
<%@ page import="gui.todolist.service.TarefaService" %>
<%@ page import="gui.todolist.model.Tarefa" %>
<%@ page import="gui.todolist.model.SubTarefa" %>

<%
    // Recupera o ID da tarefa pai para listar os itens específicos
    long tarefaId = ParamUtil.getLong(renderRequest, "tarefaId");
    Tarefa pai = TarefaService.getInstance().getTarefa(tarefaId);
%>

<div class="container mt-4">
    <%-- Cabeçalho com botão de voltar e título dinâmico --%>
    <div class="d-flex justify-content-between align-items-center mb-3">
        <portlet:renderURL var="backURL">
            <portlet:param name="mvcPath" value="/view.jsp" />
        </portlet:renderURL>
        <a href="${backURL}" class="btn btn-sm btn-outline-secondary">← Voltar</a>

        <h4 class="m-0">
            Itens de: <strong><c:out value="<%= (pai != null) ? pai.getDescricao() : "Tarefa não encontrada" %>" /></strong>
        </h4>
    </div>

    <%-- Card para adicionar nova subtarefa --%>
    <div class="card shadow-sm border-0 mb-4">
        <div class="card-header bg-white">
            <portlet:actionURL name="adicionarSubTarefa" var="addSubURL" />
            <form action="${addSubURL}" method="post" class="d-flex m-0">
                <input type="hidden" name="<portlet:namespace/>tarefaId" value="<%= tarefaId %>" />
                <input type="text" name="<portlet:namespace/>descricaoSub"
                       class="form-control" placeholder="Adicionar nova subtarefa..." required />
                <button type="submit" class="btn btn-primary ml-2">➕</button>
            </form>
        </div>

        <%-- Listagem de Subtarefas --%>
        <ul class="list-group list-group-flush">
            <%
            if (pai != null && !pai.getSubTarefas().isEmpty()) {
                for (SubTarefa sub : pai.getSubTarefas()) {
            %>
                <li class="list-group-item d-flex justify-content-between align-items-center">
                    <div>
                        <%-- Ação de alternar status (Concluir/Abrir) --%>
                        <portlet:actionURL name="concluirSubTarefa" var="toggleSubURL">
                            <portlet:param name="tarefaId" value="<%= String.valueOf(tarefaId) %>" />
                            <portlet:param name="subId" value="<%= String.valueOf(sub.getId()) %>" />
                        </portlet:actionURL>

                        <a href="${toggleSubURL}" class="text-decoration-none">
                            <%= sub.isConcluida() ? "✅" : "⬜" %>
                        </a>

                        <%-- Descrição protegida contra XSS --%>
                        <span class="ml-2 <%= sub.isConcluida() ? "text-muted text-strikethrough" : "" %>">
                            <c:out value="<%= sub.getDescricao() %>" />
                        </span>
                    </div>

                    <%-- Ações de Edição e Exclusão --%>
                    <div class="btn-group">
                        <portlet:renderURL var="editSubURL">
                            <portlet:param name="mvcPath" value="/edit_subtarefa.jsp" />
                            <portlet:param name="tarefaId" value="<%= String.valueOf(tarefaId) %>" />
                            <portlet:param name="subId" value="<%= String.valueOf(sub.getId()) %>" />
                        </portlet:renderURL>
                        <a href="${editSubURL}" class="btn btn-sm btn-light border text-primary" title="Editar">✏️</a>

                        <portlet:actionURL name="excluirSubTarefa" var="deleteSubURL">
                            <portlet:param name="tarefaId" value="<%= String.valueOf(tarefaId) %>" />
                            <portlet:param name="subId" value="<%= String.valueOf(sub.getId()) %>" />
                        </portlet:actionURL>
                        <a href="${deleteSubURL}" class="btn btn-sm btn-light border text-danger"
                           onclick="return confirm('Deseja realmente excluir este item?')" title="Excluir">🗑️</a>
                    </div>
                </li>
            <%
                }
            } else if (pai != null) {
            %>
                <li class="list-group-item text-center text-muted py-4">
                    Nenhuma subtarefa adicionada ainda.
                </li>
            <% } %>
        </ul>
    </div>
</div>

<style>
    .text-strikethrough { text-decoration: line-through; }
</style>