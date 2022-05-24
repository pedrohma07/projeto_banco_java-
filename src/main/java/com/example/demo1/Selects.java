package com.example.demo1;


import javafx.scene.control.Alert;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Selects {
    public static String consultaDadosTrans(int numConta, int numAgencia, String coluna) {
        String sql ="SELECT * FROM cliente INNER JOIN conta ON cliente.cpf = conta.cpf_cliente  where numero_conta = "+numConta+" and agencia = "+numAgencia;
        Connection conexao = ConexaoBD.getConnection();
        try {
            PreparedStatement stm = conexao.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return rs.getString(coluna);
            }else {
                return "";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }
    public static void consultaConta(int numConta, int numAgencia) {
        String sql ="SELECT * FROM cliente INNER JOIN conta ON cliente.cpf = conta.cpf_cliente  where numero_conta = "+numConta+" and agencia = "+numAgencia;
        Connection conexao = ConexaoBD.getConnection();
        try {
            PreparedStatement stm = conexao.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if(rs.next() == false){
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setContentText("Cliente não existe\n");
                alerta.showAndWait();
                TransferenciaController.verificaConta = false;
            }else{
                TransferenciaController.verificaConta = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void efetuarTransferencia(int numConta, int numAgencia, double valorDaTransacao, int saldoDodestinatario){
        String sql ="SELECT * FROM cliente INNER JOIN conta ON cliente.cpf = conta.cpf_cliente  where numero_conta = "+numConta+" and agencia = "+numAgencia;
        Connection conexao = ConexaoBD.getConnection();
    }
}