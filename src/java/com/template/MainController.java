package com.template;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.template.model.dto.UsuarioDTO;
import com.template.model.dao.UsuarioDAO;

public class MainController implements Initializable {

    @FXML private Button btnSalvar;
    @FXML private Button btnDeletar;
    @FXML private Button btnAtualizar;
    @FXML private Button btnLimpar;
    @FXML private TableView<UsuarioDTO> tblEspecialidadesMedicas;

    @FXML private TableColumn<UsuarioDTO, Integer> colId;
    @FXML private TableColumn<UsuarioDTO, String> colNome;
    @FXML private TableColumn<UsuarioDTO, String> colEmail;
    @FXML private TableColumn<UsuarioDTO, String> colSenha;
    @FXML private TableColumn<UsuarioDTO, String> colLogin;
    @FXML private TableColumn<UsuarioDTO, String> colEspecialidade;

    @FXML private TextField txtNome;
    @FXML private TextField txtEmail;
    @FXML private TextField txtSenha;
    @FXML private TextField txtLogin;
    @FXML private TextField txtEspecialidade;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colSenha.setCellValueFactory(new PropertyValueFactory<>("senha"));
        colLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        colEspecialidade.setCellValueFactory(new PropertyValueFactory<>("especialidade"));

        carregarUsuarios();
    }

    @FXML
    private void carregarUsuarios() {
        UsuarioDAO objUsuarioDAO = new UsuarioDAO();
        List<UsuarioDTO> listaUsuarios = objUsuarioDAO.selecionarUsuarios();
        tblEspecialidadesMedicas.setItems(FXCollections.observableArrayList(listaUsuarios));
    }

    @FXML
    private void carregarCampos() {
        UsuarioDTO objUsuarioDTO = tblEspecialidadesMedicas.getSelectionModel().getSelectedItem();

        if (objUsuarioDTO != null) {
            txtNome.setText(objUsuarioDTO.getNome());
            txtEmail.setText(objUsuarioDTO.getEmail());
            txtSenha.setText(objUsuarioDTO.getSenha());
            txtLogin.setText(objUsuarioDTO.getLogin());
            txtEspecialidade.setText(objUsuarioDTO.getEspecialidade());
        }
    }

    @FXML
    private void btnSalvarAction(ActionEvent event) {
        String nome = txtNome.getText();
        String email = txtEmail.getText();
        String senha = txtSenha.getText();
        String login = txtLogin.getText();
        String especialidade = txtEspecialidade.getText();

        UsuarioDTO objusuariodto = new UsuarioDTO();



        objusuariodto.setNome(nome);
        objusuariodto.setEmail(email);
        objusuariodto.setSenha(senha);
        objusuariodto.setLogin(login);
        objusuariodto.setEspecialidade(especialidade);

        UsuarioDAO objusuariodao = new UsuarioDAO();
        objusuariodao.cadastrarUsuario(objusuariodto);

        carregarUsuarios();


        txtNome.clear();
        txtEmail.clear();
        txtSenha.clear();
        txtLogin.clear();
        txtEspecialidade.clear();
    }


    @FXML
    private void btnLimparAction(ActionEvent event) {
        txtNome.clear();
        txtEmail.clear();
        txtSenha.clear();
        txtLogin.clear();
        txtEspecialidade.clear();


        tblEspecialidadesMedicas.getSelectionModel().clearSelection();
    }

    @FXML
    private void btnDeletarAction(ActionEvent event) {

        UsuarioDTO usuarioSelecionado = tblEspecialidadesMedicas.getSelectionModel().getSelectedItem();

        if (usuarioSelecionado != null) {
            UsuarioDAO dao = new UsuarioDAO();

            dao.excluirUsuario(usuarioSelecionado.getId());


            carregarUsuarios();
            btnLimparAction(null);
        } else {
            System.out.println("Selecione um funcionário na tabela para deletar.");
        }
    }


    @FXML
    private void btnAtualizarAction(ActionEvent event) {
        UsuarioDTO usuarioSelecionado = tblEspecialidadesMedicas.getSelectionModel().getSelectedItem();

        if (usuarioSelecionado != null) {

            usuarioSelecionado.setNome(txtNome.getText());
            usuarioSelecionado.setEmail(txtEmail.getText());
            usuarioSelecionado.setSenha(txtSenha.getText());
            usuarioSelecionado.setLogin(txtLogin.getText());
            usuarioSelecionado.setEspecialidade(txtEspecialidade.getText());

            UsuarioDAO dao = new UsuarioDAO();
            dao.alterarUsuario(usuarioSelecionado);

            carregarUsuarios();
            btnLimparAction(null);
        } else {
            System.out.println("Selecione um funcionário na tabela para atualizar.");
        }
    }
}