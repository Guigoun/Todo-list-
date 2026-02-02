<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/init.jsp" %>
<%@ page import="gui.todolist.service.TarefaService" %>
<%@ page import="gui.todolist.model.*" %>
<%@ page import="java.util.List" %>

<%
    List<Tarefa> lista = (List<Tarefa>) request.getAttribute("tarefas");
    if (lista == null) lista = TarefaService.getInstance().getTarefas();
%>

<div class="container-fluid mt-4">
    <div class="d-flex justify-content-between align-items-center mb-4 p-3 bg-white shadow-sm rounded border">
        <h3 class="text-primary m-0">📋 Minhas Tarefas</h3>
        <portlet:actionURL name="adicionarTarefa" var="addURL" />
        <form action="${addURL}" method="post" class="d-flex m-0">
            <%-- Validação no cliente: required --%>
            <input type="text" name="<portlet:namespace/>descricao" class="form-control" placeholder="Nova tarefa..." required maxlength="100" />
            <button type="submit" class="btn btn-primary ml-2">➕</button>
        </form>
    </div>

    <div class="list-group">
        <% for (Tarefa t : lista) {
            int total = t.getSubTarefas().size();
            int feitas = 0;
            for(SubTarefa s : t.getSubTarefas()) { if(s.isConcluida()) feitas++; }
            int pct = (total > 0) ? (feitas * 100 / total) : 0;
        %>
            <div class="list-group-item d-flex justify-content-between align-items-center p-3 mb-2 border rounded shadow-sm">
                <div class="d-flex align-items-center flex-grow-1">
                    <portlet:actionURL name="concluirTarefa" var="cURL"><portlet:param name="tarefaId" value="<%= String.valueOf(t.getId()) %>" /></portlet:actionURL>
                    <a href="${cURL}" class="mr-3" style="font-size: 1.5em; text-decoration: none;"><%= t.isConcluida() ? "✅" : "⬜" %></a>

                    <div class="w-100">
                        <span style="<%= t.isConcluida() ? "text-decoration:line-through;color:#aaa;" : "font-weight:600;" %>">
                            <%-- Segurança XSS: c:out --%>
                            <c:out value="<%= t.getDescricao() %>" />
                        </span>
                        <% if (total > 0) { %>
                            <div class="progress mt-2" style="height: 6px; background-color: #eee;">
                                <div class="progress-bar bg-success" style="width: <%= pct %>%;"></div>
                            </div>
                            <small class="text-muted"><%= feitas %>/<%= total %> sub</small>
                        <% } %>
                    </div>
                </div>

                <div class="btn-group ml-3">
                    <portlet:renderURL var="sURL"><portlet:param name="mvcPath" value="/subtarefas.jsp" /><portlet:param name="tarefaId" value="<%= String.valueOf(t.getId()) %>" /></portlet:renderURL>
                    <a href="${sURL}" class="btn btn-sm btn-light border">Itens</a>

                    <portlet:renderURL var="editURL"><portlet:param name="mvcPath" value="/edit.jsp" /><portlet:param name="tarefaId" value="<%= String.valueOf(t.getId()) %>" /></portlet:renderURL>
                    <a href="${editURL}" class="btn btn-sm btn-light border text-primary">✏️</a>

                    <portlet:actionURL name="excluirTarefa" var="dURL"><portlet:param name="tarefaId" value="<%= String.valueOf(t.getId()) %>" /></portlet:actionURL>
                    <a href="${dURL}" class="btn btn-sm btn-light border text-danger" onclick="return confirm('Excluir?')">🗑️</a>
                </div>
            </div>
        <% } %>
    </div>
</div>