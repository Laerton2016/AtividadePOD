/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.node1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laerton
 */
public class Programa {
 
    private static final String HOST = "localhost";
    private static final int PORTANODE2 = 1092;
    private static final int PORTANODE3 = 1093;
    
    public static void main(String[] args) {
        String op1 = "sum(1,2)";
        String op2 = "diff(2,1)";
        String resposta="";
        try {
            resposta = ConexSocket.enviaMensagem(op1, PORTANODE3, HOST);
        } catch (Exception ex) {
            Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.err.println(resposta);
        
        try {
            resposta = ConexSocket.enviaMensagem(op2, PORTANODE2, HOST);
        } catch (Exception ex) {
            Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.err.println(resposta);
        /*
        try {
            resposta = ConexSocket.enviaMensagem(op2, PORTANODE2, HOST);
        } catch (Exception ex) {
            Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.err.println(resposta);*/
  
    }
    
    
    
}
