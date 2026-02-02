package gui.todolist.model;

public class SubTarefa {
    private long id;
    private String descricao;
    private boolean concluida;

    public SubTarefa(String descricao) {
        this.id = System.nanoTime();
        this.descricao = descricao;
        this.concluida = false;
    }

    public long getId() { return id; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public boolean isConcluida() { return concluida; }
    public void setConcluida(boolean concluida) { this.concluida = concluida; }
}