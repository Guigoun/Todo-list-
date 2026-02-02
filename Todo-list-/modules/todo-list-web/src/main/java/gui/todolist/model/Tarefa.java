package gui.todolist.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Entidade principal que representa uma Tarefa no sistema.
 * Implementa o requisito de "Cadastro e Listagem de Tarefas".
 */
public class Tarefa {

    private long id;
    private String descricao;
    private boolean concluida;

    /**
     * Relacionamento Um-para-Muitos: Uma tarefa pode possuir múltiplas subtarefas.
     * Atende ao requisito de "Gerenciador de Tarefas" com itens vinculados.
     */
    private List<SubTarefa> subTarefas;

    /**
     * Construtor da classe Tarefa.
     * @param id Identificador único gerado pelo serviço.
     * @param descricao Texto descritivo, deve ser sanitizado contra XSS na camada de controle.
     */
    public Tarefa(long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
        this.concluida = false;
        // Inicialização preventiva para evitar NullPointerException ao manipular subtarefas.
        this.subTarefas = new ArrayList<>();
    }

    // --- GETTERS E SETTERS ---
    // Essenciais para a integração com as Taglibs do Liferay (Liferay UI e Lexicon).

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

    /**
     * Indica se a tarefa principal foi finalizada.
     * Utilizado para estilização visual (ex: texto tachado) na interface.
     */
    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    // --- MÉTODOS DE GERENCIAMENTO DE FILHOS (SUBTAREFAS) ---

    public List<SubTarefa> getSubTarefas() {
        return subTarefas;
    }

    public void setSubTarefas(List<SubTarefa> subTarefas) {
        this.subTarefas = subTarefas;
    }

    /**
     * Adiciona uma nova subtarefa à lista desta tarefa.
     * Implementa a funcionalidade de "Adicionar itens às tarefas".
     */
    public void adicionarSubTarefa(SubTarefa subTarefa) {
        this.subTarefas.add(subTarefa);
    }
}