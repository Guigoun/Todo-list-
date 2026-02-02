package gui.todolist.service;

import gui.todolist.model.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Serviço responsável pelo gerenciamento de credenciais e usuários.
 * Implementa a lógica de persistência em memória para o sistema de autenticação.
 */
public class UsuarioService {

    /**
     * Simulação de banco de dados em memória utilizando ArrayList.
     * Armazena os usuários cadastrados durante o tempo de execução do servidor.
     */
    private static List<Usuario> bancoDeUsuarios = new ArrayList<>();

    /**
     * Verifica a existência de um usuário para evitar duplicidade de registros.
     * @param nome Nome de usuário a ser verificado.
     * @return true se o usuário já existir (ignorando maiúsculas/minúsculas).
     */
    public boolean isUsuarioJaCadastrado(String nome) {
        for (Usuario u : bancoDeUsuarios) {
            // Validação de unicidade para garantir integridade no login.
            if (u.getNome().equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Registra um novo usuário no sistema.
     * Atende ao requisito de "Cadastrar múltiplos usuários".
     * @param usuario Objeto contendo nome e senha sanitizados.
     */
    public void cadastrar(Usuario usuario) {
        bancoDeUsuarios.add(usuario);
    }

    /**
     * Retorna a listagem completa de usuários para fins de validação interna.
     */
    public List<Usuario> getTodosUsuarios() {
        return bancoDeUsuarios;
    }
}