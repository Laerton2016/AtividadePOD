/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.node3;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laerton
 */
public class Programa {
    
    
    private static final int PORTASERVER = 10999;
    
    public static void main(String[] args) {
        try {
            String[] retorno = ConexSocket.recebeMensagem(PORTASERVER).split(";");
            System.out.println("Resultado é: " + Equacao.solucao(Double.valueOf(retorno[0]) , Double.valueOf(retorno[1])));
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}
