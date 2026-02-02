package gui.todolist.service;

import gui.todolist.model.SubTarefa;
import gui.todolist.model.Tarefa;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de serviço responsável por centralizar
 * a lógica de negócio relacionada às Tarefas e SubTarefas.
 *
 * Implementa o padrão Singleton para garantir
 * uma única instância do serviço durante a execução.
 */
public class TarefaService {

    // Instância única da classe (Singleton)
    private static TarefaService _instance;

    /**
     * Retorna a instância única do serviço.
     * Se ainda não existir, cria uma nova.
     */
    public static TarefaService getInstance() {
        if (_instance == null) _instance = new TarefaService();
        return _instance;
    }

    // Lista em memória que armazena as tarefas
    private List<Tarefa> tarefas = new ArrayList<>();

    // Controla o próximo ID das tarefas
    private long proximoId = 1;

    /**
     * Retorna a lista de tarefas cadastradas.
     */
    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    /**
     * Cria e adiciona uma nova tarefa à lista.
     *
     * @param d descrição da tarefa
     */
    public void adicionarTarefa(String d) {
        tarefas.add(new Tarefa(proximoId++, d));
    }

    /**
     * Remove uma tarefa pelo seu ID.
     *
     * @param id identificador da tarefa
     */
    public void removerTarefa(long id) {
        tarefas.removeIf(t -> t.getId() == id);
    }

    /**
     * Busca uma tarefa pelo ID.
     *
     * @param id identificador da tarefa
     * @return objeto Tarefa ou null se não encontrado
     */
    public Tarefa getTarefa(long id) {
        for (Tarefa t : tarefas) {
            if (t.getId() == id) return t;
        }
        return null;
    }

    /**
     * Atualiza a descrição de uma tarefa existente.
     *
     * @param id identificador da tarefa
     * @param d nova descrição
     */
    public void atualizarTarefa(long id, String d) {
        Tarefa t = getTarefa(id);
        if (t != null) t.setDescricao(d);
    }

    /**
     * Alterna o status de conclusão da tarefa.
     * Se estiver concluída, marca como aberta e vice-versa.
     *
     * @param id identificador da tarefa
     */
    public void alternarConclusao(long id) {
        Tarefa t = getTarefa(id);
        if (t != null) t.setConcluida(!t.isConcluida());
    }

    /**
     * Remove uma subtarefa de uma tarefa pai.
     *
     * @param paiId ID da tarefa principal
     * @param subId ID da subtarefa
     */
    public void excluirSubTarefa(long paiId, long subId) {
        Tarefa p = getTarefa(paiId);
        if (p != null)
            p.getSubTarefas().removeIf(s -> s.getId() == subId);
    }

    /**
     * Alterna o status de conclusão de uma subtarefa.
     *
     * @param paiId ID da tarefa principal
     * @param subId ID da subtarefa
     */
    public void alternarSubTarefa(long paiId, long subId) {
        Tarefa p = getTarefa(paiId);
        if (p != null) {
            for (SubTarefa s : p.getSubTarefas()) {
                if (s.getId() == subId) {
                    s.setConcluida(!s.isConcluida());
                    break;
                }
            }
        }
    }

    /**
     * Atualiza a descrição de uma subtarefa.
     *
     * @param paiId ID da tarefa principal
     * @param subId ID da subtarefa
     * @param d nova descrição
     */
    public void atualizarSubTarefa(long paiId, long subId, String d) {
        Tarefa p = getTarefa(paiId);
        if (p != null) {
            for (SubTarefa s : p.getSubTarefas()) {
                if (s.getId() == subId) {
                    s.setDescricao(d);
                    break;
                }
            }
        }
    }

    /**
     * Busca uma subtarefa específica a partir da tarefa pai.
     *
     * @param paiId ID da tarefa principal
     * @param subId ID da subtarefa
     * @return objeto SubTarefa ou null se não encontrado
     */
    public SubTarefa getSubTarefa(long paiId, long subId) {
        Tarefa p = getTarefa(paiId);
        if (p != null) {
            for (SubTarefa s : p.getSubTarefas()) {
                if (s.getId() == subId) return s;
            }
        }
        return null;
    }
}
