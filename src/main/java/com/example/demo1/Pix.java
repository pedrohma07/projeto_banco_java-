package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Pix implements Initializable {

    public Label saldo_atual;
    ObservableList list = FXCollections.observableArrayList();

    private String banco = "Nubank";
    private String nome = "Eraldo Mendes";
    private double valor_atual = 0.0;


    @FXML
    private TextField key_input;

    @FXML
    private TextField valor_input;

    @FXML
    private Button confirmar_button;

    @FXML
    private Separator valor_textfield;

    @FXML
    private ChoiceBox<String> keys;

    public void initialize(URL url, ResourceBundle rb){
        loadKeys();
        confirmar_button.setStyle("-fx-background-color: #5fd75f; -fx-background-radius: 15px; -fx-text-fill: #ffffff");
        keys.setStyle("-fx-background-color: #e5e4e2; -fx-text-fill: #ffffff");
    }

    private void loadKeys(){
        list.removeAll(list);
        String key_cpf = "CPF";
        String key_email = "Email";
        list.addAll(key_cpf,key_email);
        keys.getItems().addAll(list);
    }

    @FXML
    void confirmar(ActionEvent event) {
        ValidaCpf validor_cpf = new ValidaCpf();
        ValidaEmail validor_email = new ValidaEmail();

        valor_input.getText();
        key_input.getText();
        String selectedChoice = keys.getSelectionModel().getSelectedItem();


        if(selectedChoice.equals("CPF")){
            if (ValidaCpf.isCPF(key_input.getText()) == true) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setContentText(ValidaCpf.imprimeCPF(key_input.getText()) + " CPF VALIDO " + "\n Dados do recebedor: \n Nome: " + nome +"\n Banco: " + banco + "\n Valor: R$ " + valor_input.getText());
                alerta.showAndWait();

            }
            else{
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setContentText("Cpf inválido !\n");
                alerta.showAndWait();
            }
        }
        else if(selectedChoice.equals("Email")){
            if(validor_email.isValidEmailAddressRegex(key_input.getText()) == true){
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setContentText(ValidaEmail.imprimeEmail(key_input.getText()) + " EMAIL VALIDO " + "\n Dados do recebedor: \n Nome: " + nome +"\n Banco: " + banco + "\n Valor: R$ " + valor_input.getText());
                alerta.showAndWait();
            }
            else{
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setContentText("Email inválido !\n");
                alerta.showAndWait();
            }
        }
        else{
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setContentText("Chave não pode ser em branco !\n");
            alerta.showAndWait();
        }

    }

}



