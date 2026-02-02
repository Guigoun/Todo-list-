package gui.todolist.service;

import gui.todolist.model.Tarefa;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class TarefaServiceTest {

    private TarefaService _service;

    @Before
    public void setUp() {
        // Inicializa o serviço antes de cada teste
        _service = TarefaService.getInstance();
        _service.getTarefas().clear(); // Limpa a lista para garantir isolamento
    }

    @Test
    public void testAdicionarTarefa() {
        _service.adicionarTarefa("Teste Unitário");
        List<Tarefa> tarefas = _service.getTarefas();

        Assert.assertEquals(1, tarefas.size());
        Assert.assertEquals("Teste Unitário", tarefas.get(0).getDescricao());
    }

    @Test
    public void testAlternarConclusao() {
        _service.adicionarTarefa("Tarefa para concluir");
        long id = _service.getTarefas().get(0).getId();

        // Testa marcar como concluída
        _service.alternarConclusao(id);
        Assert.assertTrue(_service.getTarefa(id).isConcluida());

        // Testa desmarcar
        _service.alternarConclusao(id);
        Assert.assertFalse(_service.getTarefa(id).isConcluida());
    }

    @Test
    public void testRemoverTarefa() {
        _service.adicionarTarefa("Tarefa para remover");
        long id = _service.getTarefas().get(0).getId();

        _service.removerTarefa(id);
        Assert.assertEquals(0, _service.getTarefas().size());
    }
}