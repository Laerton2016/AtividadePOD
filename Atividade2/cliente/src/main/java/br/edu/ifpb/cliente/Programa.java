/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.cliente;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laerton
 */
public class Programa {
 
    private static final String HOST = "localhost";
    private static final int PORTANODE1 = 1091;
    private static final int PORTANODE2 = 1092;
    private static final int PORTANODE3 = 1093;
    
    public static void main(String[] args) {
        String op1 = "sum(1,2)";
        String op2 = "diff(2,1)";
        String resposta = tentativas(op1);
        System.err.println(resposta);
    //    resposta = tentativas(op2);
    //    System.err.println(resposta);
    }
    private static String tentativas(String mensagem){
        String resposta = envia(mensagem, PORTANODE3, HOST);
        if (resposta.equals("ERRO")){
            resposta = envia(mensagem, PORTANODE2, HOST);
        }
        if (resposta.equals("ERRO")){
            resposta = envia(mensagem, PORTANODE1, HOST);
        }
        return resposta;
    }
    
    private static String envia (String mensagem, int porta, String host){
        try {
            return ConexSocket.enviaMensagem(mensagem, porta, host);
        } catch (Exception ex) {
            return "ERRO";
        }
    }
}
