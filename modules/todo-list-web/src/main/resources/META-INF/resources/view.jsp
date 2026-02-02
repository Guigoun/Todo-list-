<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/init.jsp" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<portlet:actionURL name="adicionarTarefa" var="adicionarTarefaURL" />

<div class="container-fluid mt-3">
    <h3>🚀 Minha TodoList Liferay</h3>

    <%-- Formulário --%>
    <form action="${adicionarTarefaURL}" method="post" class="mb-4">
        <div class="input-group">
            <input type="text" name="<portlet:namespace/>titulo" class="form-control" placeholder="Nova tarefa..." required />
            <div class="input-group-append">
                <button class="btn btn-primary" type="submit">➕ Adicionar</button>
            </div>
        </div>
    </form>

    <%-- Tabela --%>
    <table class="table table-striped table-bordered">
        <thead class="thead-dark">
            <tr>
                <th>Tarefa</th>
                <th style="width: 120px;">Status</th>
                <th style="width: 150px;">Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listaTarefas}" var="tarefa" varStatus="status">
                <tr>
                    <td style="${tarefa.concluida ? 'text-decoration: line-through; color: gray;' : ''}">
                        ${tarefa.titulo}
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${tarefa.concluida}">
                                <span class="badge badge-success">✅ Feito</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge badge-warning">🕑 Pendente</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <%-- URLs --%>
                        <portlet:actionURL name="concluirTarefa" var="concluirURL">
                            <portlet:param name="tarefaIndex" value="${status.index}" />
                        </portlet:actionURL>

                        <portlet:actionURL name="excluirTarefa" var="excluirURL">
                            <portlet:param name="tarefaIndex" value="${status.index}" />
                        </portlet:actionURL>

                        <%-- CORREÇÃO AQUI: mvcPath virou um param --%>
                        <portlet:renderURL var="editarURL">
                            <portlet:param name="mvcPath" value="/edit.jsp" />
                            <portlet:param name="tarefaIndex" value="${status.index}" />
                            <portlet:param name="titulo" value="${tarefa.titulo}" />
                        </portlet:renderURL>

                        <%-- Botões --%>
                        <div class="btn-group">
                            <a href="${editarURL}" class="btn btn-sm btn-info" title="Editar">✏</a>

                            <c:if test="${!tarefa.concluida}">
                                <a href="${concluirURL}" class="btn btn-sm btn-success" title="Concluir">✔</a>
                            </c:if>

                            <a href="${excluirURL}" class="btn btn-sm btn-danger" title="Excluir" onclick="return confirm('Tem certeza?');">🗑</a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>