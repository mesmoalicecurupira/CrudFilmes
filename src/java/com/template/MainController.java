package com.template;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class MainController {
    @FXML private Button btnSalvar;
    @FXML private Button btnId;
    @FXML private Button btnAtualizar;
    @FXML private Button btnExcluir;
    @FXML private Button btnLimpar;
    @FXML private TextField txtId;
    @FXML private TextField txtNome;
    @FXML private TextField txtClassificacao;
    @FXML private TextField txtCategoria;
    @FXML private TextField txtAtores;
    @FXML private TableView<FilmesDTO> tblFilmes;
    @FXML private TableColumn<FilmesDTO, Integer> colId;//essas tabelas so aceitam obj FilmesDTO
    @FXML private TableColumn<FilmesDTO, String> colNome;
    @FXML private TableColumn<FilmesDTO, String> colClassificacao;
    @FXML private TableColumn<FilmesDTO, String> colAtores;
    @FXML private TableColumn<FilmesDTO, String> colCategoria;

    @FXML
    private void btnSalvarAction (ActionEvent event){
        String nome= txtNome.getText();
        String classificacao = txtClassificacao.getText();
        String atores = txtAtores.getText();
        String categoria = txtCategoria.getText();

        FilmesDTO ObjfilmesDTO = new FilmesDTO();
        ObjfilmesDTO.setNome(nome);
        ObjfilmesDTO.setClassificacao(classificacao);
        ObjfilmesDTO.setCategoria(categoria);
        ObjfilmesDTO.setAtores(atores);

        FilmesDAO objfilmesDAO = new FilmesDAO();
        objfilmesDAO.cadastrarFilmes(ObjfilmesDTO);

        carregarFilmes();
    }

    @FXML
    private void initialize()
    {
        //PropertyValueFactory associa a coluna da tabela ao atributo específico do FilmesDTO.
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colAtores.setCellValueFactory(new PropertyValueFactory<>("atores"));
        colClassificacao.setCellValueFactory(new PropertyValueFactory<>("classificacao"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));

        carregarFilmes();
    }

    @FXML
    private void carregarFilmes() {
        // Busca a lista de filmes do banco de dados
        FilmesDAO objFilmesDAO = new FilmesDAO();
        ArrayList<FilmesDTO> listaDeFilmes = objFilmesDAO.lerFilmes();
        tblFilmes.setItems(FXCollections.observableArrayList(listaDeFilmes));
    }
    @FXML
    private void btnLimparAction(ActionEvent event)
    {
        txtCategoria.clear();
        txtAtores.clear();
        txtClassificacao.clear();
        txtNome.clear();
    }


    @FXML
    private void btnAtualizarAction(ActionEvent event)
    {
        FilmesDTO ObjfilmesDTO = new FilmesDTO();

        ObjfilmesDTO.setId(Integer.valueOf(txtId.getText()));
        ObjfilmesDTO.setNome(txtNome.getText());
        ObjfilmesDTO.setClassificacao(txtClassificacao.getText());
        ObjfilmesDTO.setAtores(txtAtores.getText());
        ObjfilmesDTO.setCategoria(txtCategoria.getText());

        FilmesDAO filmesDAO = new FilmesDAO();
        filmesDAO.atualizarFilmes(ObjfilmesDTO);

        carregarFilmes();
    }
    @FXML
    private void btnExcluirAction(ActionEvent event) {
        FilmesDTO ObjfilmesDTO = new FilmesDTO();
        Integer id = Integer.valueOf(txtId.getText());
        ObjfilmesDTO.setId(id);

        FilmesDAO objFilmesDAO = new FilmesDAO();
        objFilmesDAO.deletarFilmes(ObjfilmesDTO);

        carregarFilmes();
    }

    @FXML
    private void carregarCampos()
    {
                                        //entrei na tabela   linha da tabela
        FilmesDTO filmesDTO = tblFilmes.getSelectionModel().getSelectedItem();

        if(filmesDTO != null){
            txtId.setText(String.valueOf(filmesDTO.getId()));
            txtNome.setText((filmesDTO.getNome()));
            txtClassificacao.setText((filmesDTO.getClassificacao()));
            txtAtores.setText((filmesDTO.getAtores()));
            txtCategoria.setText((filmesDTO.getCategoria()));
        }

    }
}

