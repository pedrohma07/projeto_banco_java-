package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ConfirmacaoDeTransController implements Initializable {

    @FXML
    private Label ConfirmationNome;

    @FXML
    private Label confirmationAgencia;

    @FXML
    private Label confirmationConta;

    @FXML
    private Button fecharTela;

    @FXML
    private Button confirmarTransacaoButton;

    @FXML
    private Label nomeTitularConfir;

    @FXML
    private Label numAgenciaConfir;

    @FXML
    private Label numContaConfir;

    @FXML
    private Label saldoFim;

    @FXML
    private Label valorDaTranferencia;

    @FXML
    void cancelarTransacao(ActionEvent event) {
        Stage stage = (Stage) fecharTela.getScene().getWindow();
        stage.close();
    }

    @FXML
    void confirmarTransacao(ActionEvent event) {
        String sqlSelect = "select saldo from conta where numero_conta = (?)";
        String sqlUpdate ="UPDATE conta SET saldo = (?)+(?) WHERE numero_conta = (?)";
        Connection conexao = ConexaoBD.getConnection();
        try {
            PreparedStatement stm = conexao.prepareStatement(sqlUpdate);
            PreparedStatement stm2 = conexao.prepareStatement(sqlSelect);
            stm2.setString(1, Integer.toString(TransferenciaController.contaAux));
            ResultSet rs = stm2.executeQuery();
            rs.next();

            stm.setString(1, rs.getString("saldo"));
            stm.setString(2, Double.toString(TransferenciaController.valorTranAux));
            stm.setString(3, Integer.toString(TransferenciaController.contaAux));
            stm.execute();

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText("Transação efetuada com sucesso\n");
            alerta.showAndWait();


            Stage stage = (Stage) confirmarTransacaoButton.getScene().getWindow();
            stage.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomeTitularConfir.setText(Selects.consultaDadosTrans(TransferenciaController.contaAux, TransferenciaController.agenciaAux, "nome"));
        numAgenciaConfir.setText(Integer.toString(TransferenciaController.agenciaAux));
        numContaConfir.setText(Integer.toString(TransferenciaController.contaAux));
        valorDaTranferencia.setText(String.format("%.2f", TransferenciaController.valorTranAux));
        saldoFim.setText(String.format("%.2f",TransferenciaController.saldoFimAux));
    }
}
