package gui.todolist.model;

/**
 * Entidade que representa um item (subtarefa) vinculado a uma Tarefa principal.
 * Atende aos requisitos de gerenciamento de itens do desafio Liferay.
 */
public class SubTarefa {
    private long id;
    private String descricao;
    private boolean concluida;

    /**
     * Construtor para novas subtarefas.
     * @param descricao Texto da atividade. Deve ser sanitizado contra XSS antes da criação.
     */
    public SubTarefa(String descricao) {
        // Utiliza nanoTime para gerar um ID único em memória, garantindo isolamento nos testes.
        this.id = System.nanoTime();
        this.descricao = descricao;
        this.concluida = false; // Por padrão, toda nova subtarefa inicia como "Pendente".
    }

    // Getters e Setters: Essenciais para a manipulação via Reflection e Taglibs do Liferay.

    public long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Verifica o estado de conclusão da subtarefa.
     * Utilizado para calcular a barra de progresso no Card da tarefa pai.
     */
    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }
}