package com.template.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

import com.template.model.dto.UsuarioDTO;
import com.template.service.UsuarioService;
import com.template.validator.UsuarioValidator;

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

    @FXML private Label lblMensagem;

    private final UsuarioService usuarioService = new UsuarioService();
    private final UsuarioValidator usuarioValidator = new UsuarioValidator();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellFactory(col -> new TableCell<UsuarioDTO, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.valueOf(getIndex() + 1));
                }
            }
        });

        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colSenha.setCellValueFactory(new PropertyValueFactory<>("senha"));
        colLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        colEspecialidade.setCellValueFactory(new PropertyValueFactory<>("especialidade"));

        carregarUsuarios();
    }

    @FXML
    private void carregarUsuarios() {
        tblEspecialidadesMedicas.setItems(FXCollections.observableArrayList(usuarioService.listarUsuarios()));
    }

    @FXML
    private void carregarCampos() {
        UsuarioDTO objUsuarioDTO = tblEspecialidadesMedicas.getSelectionModel().getSelectedItem();

        if (objUsuarioDTO != null) {
            lblMensagem.setText("");
            txtNome.setText(objUsuarioDTO.getNome());
            txtEmail.setText(objUsuarioDTO.getEmail());
            txtSenha.setText(objUsuarioDTO.getSenha());
            txtLogin.setText(objUsuarioDTO.getLogin());
            txtEspecialidade.setText(objUsuarioDTO.getEspecialidade());
        }
    }

    @FXML
    private void btnSalvarAction(ActionEvent event) {
        boolean valido = usuarioValidator.validarCamposPreenchidos(
                txtNome.getText(),
                txtEmail.getText(),
                txtSenha.getText(),
                txtLogin.getText(),
                txtEspecialidade.getText()
        );

        if (!valido) {
            exibirMensagem("Todos os campos devem ser preenchidos!", true);
            return;
        }

        usuarioService.cadastrarUsuario(
                txtNome.getText(),
                txtEmail.getText(),
                txtSenha.getText(),
                txtLogin.getText(),
                txtEspecialidade.getText()
        );

        carregarUsuarios();
        limparCamposFormulario();
        exibirMensagem("Usuário cadastrado com sucesso!", false);
    }

    @FXML
    private void btnLimparAction(ActionEvent event) {
        limparCamposFormulario();
        lblMensagem.setText("");
    }

    @FXML
    private void btnDeletarAction(ActionEvent event) {
        UsuarioDTO usuarioSelecionado = tblEspecialidadesMedicas.getSelectionModel().getSelectedItem();

        if (usuarioSelecionado != null) {
            usuarioService.deletarUsuario(usuarioSelecionado.getId());
            carregarUsuarios();
            limparCamposFormulario();
            exibirMensagem("Usuário deletado com sucesso!", false);
        } else {
            exibirMensagem("Selecione um funcionário na tabela para deletar.", true);
        }
    }

    @FXML
    private void btnAtualizarAction(ActionEvent event) {
        UsuarioDTO usuarioSelecionado = tblEspecialidadesMedicas.getSelectionModel().getSelectedItem();

        if (usuarioSelecionado == null) {
            exibirMensagem("Selecione um funcionário na tabela para atualizar.", true);
            return;
        }

        boolean valido = usuarioValidator.validarCamposPreenchidos(
                txtNome.getText(),
                txtEmail.getText(),
                txtSenha.getText(),
                txtLogin.getText(),
                txtEspecialidade.getText()
        );

        if (!valido) {
            exibirMensagem("Todos os campos devem ser preenchidos para atualizar!", true);
            return;
        }

        usuarioService.atualizarUsuario(
                usuarioSelecionado,
                txtNome.getText(),
                txtEmail.getText(),
                txtSenha.getText(),
                txtLogin.getText(),
                txtEspecialidade.getText()
        );

        carregarUsuarios();
        limparCamposFormulario();
        exibirMensagem("Usuário atualizado com sucesso!", false);
    }

    private void limparCamposFormulario() {
        txtNome.clear();
        txtEmail.clear();
        txtSenha.clear();
        txtLogin.clear();
        txtEspecialidade.clear();
        tblEspecialidadesMedicas.getSelectionModel().clearSelection();
    }

    private void exibirMensagem(String texto, boolean isErro) {
        lblMensagem.setText(texto);
        lblMensagem.setStyle(isErro ? "-fx-text-fill: red;" : "-fx-text-fill: green;");
    }
}