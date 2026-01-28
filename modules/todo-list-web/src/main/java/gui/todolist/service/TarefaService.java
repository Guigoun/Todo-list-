package gui.todolist.service;

import gui.todolist.model.Tarefa;
import gui.todolist.model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class TarefaService {

    // Lista global que guarda as tarefas de todos os usuários
    private static List<Tarefa> bancoDeTarefas = new ArrayList<>();

    public void adicionar(Tarefa tarefa) {
        bancoDeTarefas.add(tarefa);
    }

    // --- NOVO MÉTODO NECESSÁRIO ---
    // Adicionamos este método para o Portlet conseguir mostrar a lista inicial
    public List<Tarefa> getTodas() {
        return bancoDeTarefas;
    }
    // ------------------------------

    // Filtra só as tarefas do usuário que pediu
    public List<Tarefa> listarPorUsuario(Usuario usuario) {
        List<Tarefa> tarefasDoUsuario = new ArrayList<>();

        for (Tarefa t : bancoDeTarefas) {
            // Verificação extra para evitar erro se a tarefa não tiver dono (null)
            if (t.getDono() != null && t.getDono().getNome().equals(usuario.getNome())) {
                tarefasDoUsuario.add(t);
            }
        }
        return tarefasDoUsuario;
    }

    public void remover(Tarefa tarefa) {
        bancoDeTarefas.remove(tarefa);
    }
}