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
    @FXML private TableColumn<FilmesDTO, Integer> colId;
    @FXML private TableColumn<FilmesDTO, String> colNome;
    @FXML private TableColumn<FilmesDTO, Integer> colClassificacao;
    @FXML private TableColumn<FilmesDTO, String> colAtores;

    @FXML
    private void btnSalvarAction (ActionEvent event){
        String nome= txtNome.getText();
        String classificacao = txtClassificacao.getText();
        String atores = txtAtores.getText();
        String categoria = txtCategoria.getText();

        FilmesDTO Objfilmesdto = new FilmesDTO();
        Objfilmesdto.setNome(nome);
        Objfilmesdto.setClassificacao(classificacao);
        Objfilmesdto.setCategoria(categoria);
        Objfilmesdto.setAtores(atores);

        FilmesDAO objfilmesdao = new FilmesDAO();
        objfilmesdao.cadastrarFilmes(Objfilmesdto);

        carregarFilmes();
    }

    @FXML
    private void initialize()
    {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));//esse codigo se comunica com a view(.fxml)
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colAtores.setCellValueFactory(new PropertyValueFactory<>("atores"));
        colClassificacao.setCellValueFactory((new PropertyValueFactory<>("classificacao")));
        carregarFilmes();
    }

    @FXML
    private void carregarFilmes() {
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
        // 1. Criamos o objeto que vai carregar os novos dados
        FilmesDTO Objfilmesdto = new FilmesDTO();

        // 2. Preenchemos o objeto com o que o usuário digitou nas caixas de texto
        Objfilmesdto.setId(Integer.valueOf(txtId.getText()));
        Objfilmesdto.setNome(txtNome.getText());
        Objfilmesdto.setClassificacao(txtClassificacao.getText());
        Objfilmesdto.setAtores(txtAtores.getText());
        Objfilmesdto.setCategoria(txtCategoria.getText());

        // 3. Chamamos o DAO para atualizar no banco de dados (Isso estava faltando!)
        FilmesDAO filmesDAO = new FilmesDAO();
        filmesDAO.atualizarFilmes(Objfilmesdto);

        // 4. Atualiza a tabela na tela para mostrar o filme alterado
        carregarFilmes();
    }
    @FXML
    private void btnExcluirAction(ActionEvent event) {
        FilmesDTO Objfilmesdto = new FilmesDTO();
        Integer id = Integer.valueOf(txtId.getText());
        Objfilmesdto.setId(id);

        FilmesDAO objFilmesDAO = new FilmesDAO();
        objFilmesDAO.deletarFilmes(Objfilmesdto);

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

