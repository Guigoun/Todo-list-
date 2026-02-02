package gui.todolist.model;

import java.util.ArrayList;
import java.util.List;

public class Tarefa {

    private long id;
    private String descricao;
    private boolean concluida;

    // Lista de Subtarefas (Filhos)
    private List<SubTarefa> subTarefas;

    // Construtor
    public Tarefa(long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
        this.concluida = false;
        // Importante: Inicializar a lista para não dar erro de NullPointer
        this.subTarefas = new ArrayList<>();
    }

    // --- GETTERS E SETTERS (Os métodos que faltavam) ---

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    // --- MÉTODOS DE SUBTAREFAS ---

    public List<SubTarefa> getSubTarefas() {
        return subTarefas;
    }

    public void setSubTarefas(List<SubTarefa> subTarefas) {
        this.subTarefas = subTarefas;
    }

    public void adicionarSubTarefa(SubTarefa subTarefa) {
        this.subTarefas.add(subTarefa);
    }
}