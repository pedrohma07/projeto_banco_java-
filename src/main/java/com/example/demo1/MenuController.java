package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private Label agencia_menu;

    @FXML
    private Label conta_menu;

    @FXML
    private Label nome_menu;

    @FXML
    private Label saldo_menu;



    @FXML
    void telaEmprestimo(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Emprestimo.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Empréstimo");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void telaPix(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pix.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Pix");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void telaTransferencia(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("transferencia.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Transferência");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void telaExtrato(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("movimentacoes.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Movimentações");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String sql ="SELECT nome, numero_conta, agencia, saldo FROM cliente INNER JOIN conta ON cliente.cpf = (?)";
        Connection conexao = ConexaoBD.getConnection();
        try {
            PreparedStatement stm = conexao.prepareStatement(sql);
            stm.setString(1, TelaLogin.cpfAux);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                nome_menu.setText(rs.getString("nome"));
                agencia_menu.setText(rs.getString("agencia"));
                conta_menu.setText(rs.getString("numero_conta"));
                saldo_menu.setText(String.format("R$ %.2f", rs.getDouble("saldo")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
