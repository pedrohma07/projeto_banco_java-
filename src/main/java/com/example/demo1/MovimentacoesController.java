package com.example.demo1;


import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class MovimentacoesController implements Initializable {


    DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter dataFormatadaImpresao = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate dataInicialAux = null;
    LocalDate dataFinalAux = null;

    @FXML
    private TableColumn<Movimentacoes, String> colunaData;

    @FXML
    private TableColumn<Movimentacoes, String> colunaDestino;

    @FXML
    private TableColumn<Movimentacoes, String> colunaValor;

    @FXML
    private DatePicker dtFinal;

    @FXML
    private DatePicker dtInicial;

    @FXML
    private TableView<Movimentacoes> tabelaMovimentacoes;

    ObservableList<Movimentacoes> list = FXCollections.observableArrayList();

    @FXML
    void pesquisar(ActionEvent event) {
        list.removeAll(list);
        dataInicialAux = dtInicial.getValue();
        dataFinalAux = dtFinal.getValue();

        String sql ="SELECT * FROM movimentacoes where data_transacao between (?) and (?)";
        Connection conexao = ConexaoBD.getConnection();
        try {
            PreparedStatement stm = conexao.prepareStatement(sql);
            stm.setString(1, dataInicialAux.format(dataFormatada));
            stm.setString(2, dataFinalAux.format(dataFormatada));
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                LocalDate formatacaoImprecao = LocalDate.parse(rs.getString("data_transacao"));
                list.add(
                    new Movimentacoes(formatacaoImprecao.format(dataFormatadaImpresao),rs.getString("nome_destinatario"),String.format("R$ %.2f",rs.getDouble("valor")))
                );
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        colunaData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colunaDestino.setCellValueFactory(new PropertyValueFactory<>("nomeDestinatario"));
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valorTransacao"));

        tabelaMovimentacoes.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



}
