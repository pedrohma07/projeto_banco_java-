package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Emprestimo implements Initializable {

    ObservableList list = FXCollections.observableArrayList();

    @FXML
    private Button solicitar;

    @FXML
    private Button disponibilidade;

    @FXML
    private ChoiceBox<String> qtd;

    @FXML
    private Label saldo;

    @FXML
    private TextField valor_emprestimo;

    @FXML
    private TextField qtd_parcelas;

    public static double valor, parcelas,parcelas_emprestimo, verifica;
    public static double saldo_cliente = 1212.00;

    @FXML
    void verificar_emprestimo(ActionEvent event) {
        String selectedChoice = qtd.getSelectionModel().getSelectedItem();

        valor = Double.parseDouble(valor_emprestimo.getText());
        parcelas = Double.parseDouble(qtd_parcelas.getText());

        parcelas_emprestimo = valor / parcelas;
        verifica = saldo_cliente * 0.3;

        if(verifica > parcelas_emprestimo){
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText("Empréstimo aprovado com sucesso. Selecione a forma de pagamento ao lado!");
            alerta.showAndWait();
            loadparcelas();
        }
        else{
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("Desculpe no momento não conseguir aprovar seu empréstimo, tente novamente em 1 mês!");
            alerta.showAndWait();
        }
    }

    @FXML
    void solicitar(ActionEvent event) {

    }


    private void loadparcelas(){
        list.removeAll(list);
        double[] valores = new double[13];
        for(int i = 1; i <= 12; i++){
            valores[i] = valor / i;
            list.add(i + "- R$ " + String.format("%.2f",valores[i]));
        }
        qtd.getItems().addAll(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        solicitar.setStyle("-fx-background-color: #5fd75f; -fx-background-radius: 15px; -fx-text-fill: #ffffff");
    }
}
