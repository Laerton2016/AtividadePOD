/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.node1;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laerton
 */
public class Programa {
 
    private static final String HOST = "localhost";
    private static final int PORTA_SERVER = 10998;

    
    public static void main(String[] args) {
        Random r = new Random();
        String mensagem = r.nextInt(100) + ";" + r.nextInt(100);
        
        try {
            String retorno = ConexSocket.enviaMensagem(mensagem, PORTA_SERVER, HOST);
            if (retorno != null){
                System.out.println(retorno);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
