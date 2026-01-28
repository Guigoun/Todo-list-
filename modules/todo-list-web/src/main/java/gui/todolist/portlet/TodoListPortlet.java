package gui.todolist.portlet;

import gui.todolist.constants.TodoListPortletKeys;
import gui.todolist.model.Tarefa;
import gui.todolist.service.TarefaService;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import com.liferay.portal.kernel.util.ParamUtil;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.util.List;

@Component(
		immediate = true,
		property = {
				"com.liferay.portlet.display-category=category.sample",
				"com.liferay.portlet.header-portlet-css=/css/main.css",
				"com.liferay.portlet.instanceable=true",
				"javax.portlet.display-name=TodoList",
				"javax.portlet.init-param.template-path=/",
				"javax.portlet.init-param.view-template=/view.jsp",
				"javax.portlet.name=" + TodoListPortletKeys.TODOLIST,
				"javax.portlet.resource-bundle=content.Language",
				"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
)
public class TodoListPortlet extends MVCPortlet {

	private TarefaService service = new TarefaService();

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		List<Tarefa> tarefas = service.getTodas();


		renderRequest.setAttribute("listaTarefas", tarefas);
	super.render(renderRequest, renderResponse);}

	public void adicionarTarefa(ActionRequest actionRequest, ActionResponse actionResponse) {

		// Pega o texto do formulário
		String titulo = ParamUtil.getString(actionRequest, "titulo");

		// Salva na lista
		if (!titulo.isEmpty()) {
			service.adicionar(new Tarefa(titulo, "Criada via Liferay", null));
		}
	}

	//Método para concluir tarefa
	public void concluirTarefa(ActionRequest actionRequest, ActionResponse actionResponse) {
		// 1. Descobre qual é o número da tarefa na lista (0, 1, 2...)
		int index = ParamUtil.getInteger(actionRequest, "tarefaIndex");

		// 2. Busca a lista completa
		List<Tarefa> lista = service.getTodas();

		// 3. Se o índice for válido, marca como concluída
		if (index >= 0 && index < lista.size()) {
			lista.get(index).setConcluida(true);
		}
	}

	//Método para remover da lista
	public void excluirTarefa(ActionRequest actionRequest, ActionResponse actionResponse) {
		int index = ParamUtil.getInteger(actionRequest, "tarefaIndex");
		List<Tarefa> lista = service.getTodas();

		if (index >= 0 && index < lista.size()) {
			lista.remove(index);
		}
	}
	//Método para salvar edição
	public void atualizarTarefa(ActionRequest actionRequest, ActionResponse actionResponse) {

		// 1. Pega o índice e o novo texto
		int index = ParamUtil.getInteger(actionRequest, "tarefaIndex");
		String novoTitulo = ParamUtil.getString(actionRequest, "titulo");

		// 2. Busca a lista
		List<Tarefa> lista = service.getTodas();

		// 3. Se o índice for válido, atualiza o título
		if (index >= 0 && index < lista.size() && !novoTitulo.isEmpty()) {
			lista.get(index).setTitulo(novoTitulo);
		}
	}
}
