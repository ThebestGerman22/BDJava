package com.template.service;

import com.template.model.dao.UsuarioDAO;
import com.template.model.dto.UsuarioDTO;

import java.util.List;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public List<UsuarioDTO> listarUsuarios() {
        return usuarioDAO.selecionarUsuarios();
    }

    public void cadastrarUsuario(String nome, String email, String senha, String login, String especialidade) {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setLogin(login);
        usuario.setEspecialidade(especialidade);

        usuarioDAO.cadastrarUsuario(usuario);
    }

    public void atualizarUsuario(UsuarioDTO usuario, String nome, String email, String senha, String login, String especialidade) {
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setLogin(login);
        usuario.setEspecialidade(especialidade);

        usuarioDAO.alterarUsuario(usuario);
    }

    public void deletarUsuario(int id) {
        usuarioDAO.excluirUsuario(id);
    }
}