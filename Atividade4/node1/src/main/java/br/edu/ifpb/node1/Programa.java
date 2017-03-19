/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.node1;

import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laerton
 */
public class Programa {
 
    private static final String HOST = "localhost";
    private static final int PORTANODE2 = 1092;
    
    public static void main(String[] args) throws Exception {
        Pessoa p = new Pessoa("111.111.111-11", "Laerton Marques de Figueiredo");
        Gson gson = new Gson();
        String mensagem = gson.toJson(p);
        String retorno = ConexSocket.enviaMensagem(mensagem, PORTANODE2, HOST);
        System.out.println(retorno);
    }
}
