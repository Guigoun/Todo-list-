<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/init.jsp" %>

<%-- Imports fundamentais para resolver o erro de símbolo não encontrado --%>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="gui.todolist.service.TarefaService" %>
<%@ page import="gui.todolist.model.SubTarefa" %>

<%
    // Recuperação dos IDs via ParamUtil (Linha 6 corrigida)
    long tarefaId = ParamUtil.getLong(renderRequest, "tarefaId");
    long subId = ParamUtil.getLong(renderRequest, "subId");

    // Busca a instância da subtarefa para preencher o formulário
    SubTarefa sub = TarefaService.getInstance().getSubTarefa(tarefaId, subId);
%>

<div class="container mt-4">
    <div class="card shadow-sm border-0 col-md-6 mx-auto">
        <div class="card-header bg-white py-3">
            <h5 class="m-0">✏️ Editar Subtarefa</h5>
        </div>

        <div class="card-body">
            <% if (sub != null) { %>
                <portlet:actionURL name="atualizarSubTarefa" var="updateSubURL">
                    <portlet:param name="tarefaId" value="<%= String.valueOf(tarefaId) %>" />
                    <portlet:param name="subId" value="<%= String.valueOf(subId) %>" />
                </portlet:actionURL>

                <form action="${updateSubURL}" method="post">
                    <div class="form-group">
                        <label for="descricao">Descrição do Item:</label>
                        <%-- O valor é exibido de forma segura para evitar XSS --%>
                        <input type="text" id="descricao" name="<portlet:namespace/>descricao"
                               class="form-control" value="<c:out value='<%= sub.getDescricao() %>'/>"
                               required />
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
                <%-- Tratamento de erro caso o item não seja encontrado (ex: após reiniciar o servidor) --%>
                <div class="alert alert-danger text-center">
                    <strong>Erro:</strong> Item não encontrado ou sessão expirada. <br>
                    <small>Por favor, retorne à lista principal e tente novamente.</small>
                </div>

                <div class="text-center mt-3">
                    <portlet:renderURL var="homeURL"><portlet:param name="mvcPath" value="/view.jsp" /></portlet:renderURL>
                    <a href="${homeURL}" class="btn btn-secondary btn-sm">Voltar ao Início</a>
                </div>
            <% } %>
        </div>
    </div>
</div>