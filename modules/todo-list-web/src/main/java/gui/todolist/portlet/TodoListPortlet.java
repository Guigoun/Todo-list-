package gui.todolist.portlet;

import gui.todolist.constants.TodoListPortletKeys;
import gui.todolist.model.Tarefa;
import gui.todolist.service.TarefaService;

// Imports JAKARTA
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import jakarta.portlet.Portlet;
import jakarta.portlet.PortletException;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
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

		// PASSO 1: TROQUE "listar()" PELO NOME QUE ESTÁ NO SEU ARQUIVO TAREFASERVICE
		// Exemplo: service.getLista() ou service.buscarTarefas()
		List<Tarefa> tarefas = service.getTodas();

		if (tarefas.isEmpty()) {
			// PASSO 2: AQUI JÁ ESTÁ CORRIGIDO COM O 'null' NO FINAL
			service.adicionar(new Tarefa("Teste Liferay", "Funcionou!", null));

			// Atualiza a lista novamente com o nome correto
			tarefas = service.getTodas();
		}

		renderRequest.setAttribute("listaTarefas", tarefas);
		super.render(renderRequest, renderResponse);
	}
}