package br.edu.ifpb.node1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Dictionary;
import java.util.HashMap;
import javax.net.ssl.SSLSocket;

/**
 *
 * @author laerton
 */
public class ConexSocket {
    
    private static final String HOST = "localhost";
    //private static final int PORTANODE1 = 1091;
    private static final int PORTANODE2 = 1092;
    private static final int PORTANODE3 = 1093;
    
    
    public static String enviaMensagem (String mensagem, int porta, String host) throws IOException, Exception{
        String retorno ="";
        Socket sock = new Socket(host, porta);
        OutputStream out = sock.getOutputStream();
        out.write(mensagem.getBytes());
        //tratando o retorno
        InputStream in = sock.getInputStream();
        byte[] b = new byte[1024];
        in.read(b);
        retorno = new String(b);
        if (retorno.contains("ERROR-Conexão recusada")){ 
            throw  new Exception("Conexão com o servidor principal foi recusada");
        }
        sock.close();
        return retorno.trim();
    }
    
    public static void recebeMensagem (int porta) throws IOException{
        String mensagem = "";
        System.out.println("Servidor ativo!");
        ServerSocket server = new ServerSocket(porta);
        while(true){
            Socket sock = server.accept();
            InputStream in = sock.getInputStream();
            byte[] b = new byte[1024];
            in.read(b);
            mensagem = new String(b);
            System.out.println(mensagem);
            
            String respota =resolve(mensagem) ;
            OutputStream out = sock.getOutputStream();
            out.write(respota.getBytes());
            sock.close();
            throw  new RuntimeException();
        }
        
    }
    
    private static String resolve (String mensagem){
        String respota ="";
        try {
                respota = String.valueOf(TrataOperacao.executa(mensagem));
            } catch (Exception e) {
                try {
                    System.out.println("Node não resolve essa operação encaminhando para node3.");
                    respota =  enviaMensagem(mensagem, PORTANODE3, HOST);
                } catch (Exception e1) {
                    respota = e1.getMessage();
                }
            }finally{
            return respota;
        }
        
    }
}
