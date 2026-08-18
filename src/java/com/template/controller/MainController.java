package com.template.controller;

import com.template.model.dto.FilmesDTO;
import com.template.service.FilmesService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class MainController {
    @FXML private Button btnSalvar;
    @FXML private Button btnAtualizar;
    @FXML private Button btnExcluir;
    @FXML private Button btnLimpar;
    @FXML private TextField txtId;
    @FXML private TextField txtNome;
    @FXML private TextField txtClassificacao;
    @FXML private TextField txtAtores;
    @FXML private ComboBox<String> cmbCategoria;
    @FXML private Label lblMensagem;

    @FXML private TableView<FilmesDTO> tblFilmes;
    @FXML private TableColumn<FilmesDTO, Integer> colId;
    @FXML private TableColumn<FilmesDTO, String> colNome;
    @FXML private TableColumn<FilmesDTO, String> colClassificacao;
    @FXML private TableColumn<FilmesDTO, String> colAtores;
    @FXML private TableColumn<FilmesDTO, String> colCategoria;

    // Instanciamos o Service que cuidará da parte pesada do trabalho
    private final FilmesService filmesService = new FilmesService();

    @FXML
    private void initialize() {
        // Configuração das colunas da tabela
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colAtores.setCellValueFactory(new PropertyValueFactory<>("atores"));
        colClassificacao.setCellValueFactory(new PropertyValueFactory<>("classificacao"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));

        if (cmbCategoria != null) {
            cmbCategoria.getItems().addAll("Ação", "Comédia", "Drama", "Terror", "Ficção Científica");
        }

        btnAtualizar.setDisable(true);
        btnExcluir.setDisable(true);

        carregarFilmes();
    }

    @FXML
    private void btnSalvarAction(ActionEvent event) {
        String nome = txtNome.getText();
        String classificacao = txtClassificacao.getText();
        String atores = txtAtores.getText();
        String categoria = (cmbCategoria != null && cmbCategoria.getValue() != null) ? cmbCategoria.getValue() : "";

        // O Controller agora apenas pede para o Service salvar
        boolean sucesso = filmesService.salvarFilme(nome, classificacao, categoria, atores);

        if (sucesso) {
            carregarFilmes();
            btnLimparAction(event);
            if (lblMensagem != null) {
                lblMensagem.setText("O Filme '" + nome + "' foi cadastrado");
            }
        }
    }

    @FXML
    private void btnAtualizarAction(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtId.getText());
            String nome = txtNome.getText();
            String classificacao = txtClassificacao.getText();
            String atores = txtAtores.getText();
            String categoria = (cmbCategoria != null && cmbCategoria.getValue() != null) ? cmbCategoria.getValue() : "";

            boolean sucesso = filmesService.atualizarFilme(id, nome, classificacao, categoria, atores);

            if (sucesso) {
                carregarFilmes();
                btnLimparAction(event);
                if (lblMensagem != null) {
                    lblMensagem.setText("Filme atualizado");
                }
            }
        } catch (NumberFormatException e) {
            // Caso o ID não seja um número válido
            if (lblMensagem != null) lblMensagem.setText("Erro: ID inválido para atualização.");
        }
    }

    @FXML
    private void btnExcluirAction(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtId.getText());

            filmesService.excluirFilme(id);

            carregarFilmes();
            btnLimparAction(event);

            if (lblMensagem != null) {
                lblMensagem.setText("Filme excluído com sucesso");
            }
        } catch (NumberFormatException e) {
            if (lblMensagem != null) lblMensagem.setText("Erro: Selecione um filme para excluir.");
        }
    }

    @FXML
    private void btnLimparAction(ActionEvent event) {
        txtId.clear();
        txtNome.clear();
        txtClassificacao.clear();
        txtAtores.clear();
        if (cmbCategoria != null) cmbCategoria.setValue(null);

        btnAtualizar.setDisable(true);
        btnExcluir.setDisable(true);
        btnSalvar.setDisable(false);

        txtNome.requestFocus();
    }

    @FXML
    private void carregarCampos() {
        FilmesDTO filmesDTO = tblFilmes.getSelectionModel().getSelectedItem();

        if (filmesDTO != null) {
            txtId.setText(String.valueOf(filmesDTO.getId()));
            txtNome.setText(filmesDTO.getNome());
            txtClassificacao.setText(filmesDTO.getClassificacao());
            txtAtores.setText(filmesDTO.getAtores());
            if (cmbCategoria != null) cmbCategoria.setValue(filmesDTO.getCategoria());

            btnAtualizar.setDisable(false);
            btnExcluir.setDisable(false);
            btnSalvar.setDisable(true);
        }
    }

    @FXML
    private void carregarFilmes() {
        // Pede a lista para o Service e atualiza a tabela
        ArrayList<FilmesDTO> listaDeFilmes = filmesService.listarFilmes();
        tblFilmes.setItems(FXCollections.observableArrayList(listaDeFilmes));
    }
}