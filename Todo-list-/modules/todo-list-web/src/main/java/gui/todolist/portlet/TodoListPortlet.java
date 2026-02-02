package gui.todolist.portlet;

import gui.todolist.constants.TodoListPortletKeys;
import gui.todolist.model.SubTarefa;
import gui.todolist.model.Tarefa;
import gui.todolist.service.TarefaService;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import javax.portlet.*;

import org.osgi.service.component.annotations.Component;

/**
 * Controlador principal do Todo-list utilizando a infraestrutura MVCPortlet do Liferay.
 * Gerencia o fluxo entre as visões (JSPs) e a lógica de negócio (Service).
 */
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=category.sample",
                "javax.portlet.display-name=TodoList",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp", // Página inicial do portlet.
                "javax.portlet.name=" + TodoListPortletKeys.TODOLIST,
                "com.liferay.portlet.instanceable=false" // Portlet de instância única na página.
        },
        service = Portlet.class
)
public class TodoListPortlet extends MVCPortlet {

    /**
     * Método de renderização principal.
     * Injeta a lista de tarefas no request para exibição na view.jsp.
     */
    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
            throws IOException, PortletException {
        renderRequest.setAttribute("tarefas", TarefaService.getInstance().getTarefas());
        super.doView(renderRequest, renderResponse);
    }

    // --- AÇÕES DE TAREFA (CRUD PRINCIPAL) ---

    /**
     * Adiciona uma nova tarefa principal ao sistema.
     * Implementa validação de servidor e sanitização contra XSS.
     */
    public void adicionarTarefa(ActionRequest req, ActionResponse res) {
        String desc = ParamUtil.getString(req, "descricao");

        // Validação de backend para impedir entradas vazias ou nulas.
        if (Validator.isNotNull(desc) && !desc.trim().isEmpty()) {
            // HtmlUtil.escape neutraliza caracteres especiais para evitar execução de scripts (XSS).
            TarefaService.getInstance().adicionarTarefa(HtmlUtil.escape(desc));
        }
    }

    public void atualizarTarefa(ActionRequest req, ActionResponse res) {
        long id = ParamUtil.getLong(req, "tarefaId");
        String desc = ParamUtil.getString(req, "descricao");

        if (id > 0 && Validator.isNotNull(desc)) {
            TarefaService.getInstance().atualizarTarefa(id, HtmlUtil.escape(desc));
        }
        res.setRenderParameter("mvcPath", "/view.jsp");
    }

    public void concluirTarefa(ActionRequest req, ActionResponse res) {
        TarefaService.getInstance().alternarConclusao(ParamUtil.getLong(req, "tarefaId"));
    }

    public void excluirTarefa(ActionRequest req, ActionResponse res) {
        TarefaService.getInstance().removerTarefa(ParamUtil.getLong(req, "tarefaId"));
    }

    // --- AÇÕES DE SUBTAREFA (FUNCIONALIDADES AVANÇADAS) ---

    /**
     * Cria e vincula uma subtarefa a uma tarefa pai específica.
     */
    public void adicionarSubTarefa(ActionRequest req, ActionResponse res) {
        long tarefaId = ParamUtil.getLong(req, "tarefaId");
        String desc = ParamUtil.getString(req, "descricaoSub");

        if (Validator.isNotNull(desc)) {
            Tarefa t = TarefaService.getInstance().getTarefa(tarefaId);
            // Garante que a subtarefa seja sanitizada antes da persistência em memória.
            if (t != null) t.adicionarSubTarefa(new SubTarefa(HtmlUtil.escape(desc)));
        }
        redirecionarSub(res, tarefaId);
    }

    public void atualizarSubTarefa(ActionRequest req, ActionResponse res) {
        long tarefaId = ParamUtil.getLong(req, "tarefaId");
        long subId = ParamUtil.getLong(req, "subId");
        String desc = ParamUtil.getString(req, "descricao");

        TarefaService.getInstance().atualizarSubTarefa(tarefaId, subId, HtmlUtil.escape(desc));
        redirecionarSub(res, tarefaId);
    }

    public void concluirSubTarefa(ActionRequest req, ActionResponse res) {
        long tarefaId = ParamUtil.getLong(req, "tarefaId");
        TarefaService.getInstance().alternarSubTarefa(tarefaId, ParamUtil.getLong(req, "subId"));
        redirecionarSub(res, tarefaId);
    }

    public void excluirSubTarefa(ActionRequest req, ActionResponse res) {
        long tarefaId = ParamUtil.getLong(req, "tarefaId");
        TarefaService.getInstance().excluirSubTarefa(tarefaId, ParamUtil.getLong(req, "subId"));
        redirecionarSub(res, tarefaId);
    }

    /**
     * Centraliza o redirecionamento para a tela de subtarefas.
     * Garante que o ID da tarefa pai seja mantido no contexto da navegação.
     */
    private void redirecionarSub(ActionResponse res, long id) {
        res.setRenderParameter("mvcPath", "/subtarefas.jsp");
        res.setRenderParameter("tarefaId", String.valueOf(id));
    }
}