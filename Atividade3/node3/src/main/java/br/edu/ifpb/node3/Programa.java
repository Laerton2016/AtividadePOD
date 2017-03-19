/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.node3;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;

/**
 *
 * @author laerton
 */
    
public class Programa {
    //private static final String HOST = "localhost";
    //private static final int PORTANODE1 = 1091;
    //private static final int PORTANODE2 = 1092;
    private static final int PORTANODE3 = 1093;
    //private static final int PORTANODE4 = 1094;
    public static void main(String[] args) 
    {
       
        try {
            ConexSocket.recebeMensagem(PORTANODE3);
        } catch (Exception e) {
            
        }
    }
    
}
