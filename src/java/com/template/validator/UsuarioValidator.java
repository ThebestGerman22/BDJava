package com.template.validator;

public class UsuarioValidator {

    public boolean validarCamposPreenchidos(String nome, String email, String senha, String login, String especialidade) {
        return nome != null && !nome.trim().isEmpty() &&
                email != null && !email.trim().isEmpty() &&
                senha != null && !senha.trim().isEmpty() &&
                login != null && !login.trim().isEmpty() &&
                especialidade != null && !especialidade.trim().isEmpty();
    }
}