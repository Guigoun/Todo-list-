package gui.todolist.service;

import gui.todolist.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    private static List<Usuario> bancoDeUsuarios = new ArrayList<>();

    //Verifica se o nome já está na lista
    public boolean isUsuarioJaCadastrado(String nome) {
        for (Usuario u : bancoDeUsuarios) {

            if (u.getNome().equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
    }

    public void cadastrar(Usuario usuario) {
        bancoDeUsuarios.add(usuario);
    }

    public List<Usuario> getTodosUsuarios() {
        return bancoDeUsuarios;
    }
}