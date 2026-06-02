/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dto.UsuarioDTO;
import model.ConexaoBD;

//Parte responsável pelas operações do CRUD no banco de dados

public class UsuarioDAO {

    private static final Logger logger = Logger.getLogger(UsuarioDAO.class.getName()); //O Logger é fundamental para monitorar erros de forma técnica sem poluir a interface do utilizador

    public boolean cadastrarUsuario(UsuarioDTO usuario) {
        String sql = "INSERT INTO usuario (nome, email, senha, login, especialidade) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexao = new ConexaoBD().conectaBD();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getLogin());
            stmt.setString(5, usuario.getEspecialidade());
            stmt.execute();

            return true;

        } catch (SQLException e) {

            logger.log(Level.SEVERE, "Erro ao cadastrar utilizador", e);
            return false;
        }
    }

    public List<UsuarioDTO> selecionarUsuarios() {

        String sql = "SELECT * FROM usuario ORDER BY id ASC"; //O ORDER BY id ASC é essencial para que as informações não mudem de ordem em caso de mudanças no banco
        List<UsuarioDTO> listaUsuarios = new ArrayList<>();

        try (Connection conexao = new ConexaoBD().conectaBD();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                UsuarioDTO dto = new UsuarioDTO();
                dto.setId(rs.getInt("id"));
                dto.setNome(rs.getString("nome"));
                dto.setEmail(rs.getString("email"));
                dto.setSenha(rs.getString("senha"));
                dto.setLogin(rs.getString("login"));
                dto.setEspecialidade(rs.getString("especialidade"));
                listaUsuarios.add(dto);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao listar utilizadores", e);
        }

        return listaUsuarios;
    }

    public boolean alterarUsuario(UsuarioDTO usuario) {
        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ?, login = ?, especialidade = ? WHERE id = ?";

        try (Connection conexao = new ConexaoBD().conectaBD();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getLogin());
            stmt.setString(5, usuario.getEspecialidade());
            stmt.setInt(6, usuario.getId());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao alterar utilizador", e);
            return false;
        }
    }

    public boolean excluirUsuario(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?";

        try (Connection conexao = new ConexaoBD().conectaBD();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao excluir utilizador", e);
            return false;
        }
    }


}
