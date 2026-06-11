/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.template.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//Arquivo que gerencia a conexão com o Banco de Dados
public class ConexaoBD {

    static String conexao = "jdbc:postgresql://localhost:5433/EspecialidadesMedicas";
    static String usuario = "postgres";
    static String senha = "postgres";

    public Connection conectaBD() {
        try {
            // O DriverManager localiza automaticamente o driver JDBC apropriado na memória
            // e tenta estabelecer a conexão com a URL fornecida.
            return DriverManager.getConnection(conexao, usuario, senha);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }



}