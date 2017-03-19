/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.node2;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laerton
 */
public class Programa {
 
    
    private static final int PORTANODE2 = 1092;
    
    public static void main(String[] args) throws IOException  {
       
        try {
            ConexSocket.recebeMensagem(PORTANODE2);
        } catch (RuntimeException ex) {
            
        }
       
        
    }
}
