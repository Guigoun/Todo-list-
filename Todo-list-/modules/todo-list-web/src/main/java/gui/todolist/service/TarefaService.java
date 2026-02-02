package gui.todolist.service;

import gui.todolist.model.SubTarefa;
import gui.todolist.model.Tarefa;
import java.util.ArrayList;
import java.util.List;

public class TarefaService {
    private static TarefaService _instance;
    public static TarefaService getInstance() {
        if (_instance == null) _instance = new TarefaService();
        return _instance;
    }

    private List<Tarefa> tarefas = new ArrayList<>();
    private long proximoId = 1;

    public List<Tarefa> getTarefas() { return tarefas; }
    public void adicionarTarefa(String d) { tarefas.add(new Tarefa(proximoId++, d)); }
    public void removerTarefa(long id) { tarefas.removeIf(t -> t.getId() == id); }
    public Tarefa getTarefa(long id) {
        for (Tarefa t : tarefas) { if (t.getId() == id) return t; }
        return null;
    }
    public void atualizarTarefa(long id, String d) {
        Tarefa t = getTarefa(id);
        if (t != null) t.setDescricao(d);
    }
    public void alternarConclusao(long id) {
        Tarefa t = getTarefa(id);
        if (t != null) t.setConcluida(!t.isConcluida());
    }

    public void excluirSubTarefa(long paiId, long subId) {
        Tarefa p = getTarefa(paiId);
        if (p != null) p.getSubTarefas().removeIf(s -> s.getId() == subId);
    }
    public void alternarSubTarefa(long paiId, long subId) {
        Tarefa p = getTarefa(paiId);
        if (p != null) {
            for (SubTarefa s : p.getSubTarefas()) {
                if (s.getId() == subId) { s.setConcluida(!s.isConcluida()); break; }
            }
        }
    }
    public void atualizarSubTarefa(long paiId, long subId, String d) {
        Tarefa p = getTarefa(paiId);
        if (p != null) {
            for (SubTarefa s : p.getSubTarefas()) {
                if (s.getId() == subId) { s.setDescricao(d); break; }
            }
        }
    }
    public SubTarefa getSubTarefa(long paiId, long subId) {
        Tarefa p = getTarefa(paiId);
        if (p != null) {
            for (SubTarefa s : p.getSubTarefas()) { if (s.getId() == subId) return s; }
        }
        return null;
    }
}