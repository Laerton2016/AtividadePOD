/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.node2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laerton
 */
    
    
public class Programa {
    private static final String HOSTREDIREC = "localhost";
    private static final int PORTASERVER = 10998;
    private static final int PORTAREDIREC = 10999;
    public static void main(String[] args) {
        try {
            ConexSocket.recebeMensagem(PORTASERVER, HOSTREDIREC, PORTAREDIREC);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
