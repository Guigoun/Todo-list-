package gui.todolist.model;


import java.util.ArrayList;
import java.util.List;

public class Tarefa {
    private String titulo;
    private String descricao;
    private boolean concluida;
    private Usuario dono;

    //Lista de Subtarefas dentro da Tarefa Principal
    private List<SubTarefa> subTarefas = new ArrayList<>();

    public Tarefa(String titulo, String descricao, Usuario dono) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dono = dono;
        this.concluida = false;
    }

    //Método para subtarefa
    public void adicionarSubTarefa(SubTarefa sub) {
        this.subTarefas.add(sub);
    }

    public List<SubTarefa> getSubTarefas() {
        return subTarefas;
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public boolean isConcluida() { return concluida; }
    public void setConcluida(boolean concluida) { this.concluida = concluida; }

    public Usuario getDono() { return dono; }

    @Override
    public String toString() {
        String status = isConcluida() ? "[X]" : "[ ]";
        String base = status + " " + getTitulo() + " (" + getDescricao() + ")";

        // Mostra visualmente se tem itens dentro
        if (!subTarefas.isEmpty()) {
            base += " [Subtarefas: " + subTarefas.size() + "]";
        }
        return base;
    }
}