package br.edu.ifpb.node4;

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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;

/**
 *
 * @author laerton
 */
public class ConexSocket {
    
    private static final String HOST = "localhost";
    
    private static final int PORTANODE3 = 0;
    
    /***
     * Método cria uma conexão socket para enviar uma mensagem para o host e porta informados
     * @param mensagem - Mensagem a ser enviada
     * @param porta - Porta do host de destino
     * @param host - Host de destido
     * @return Mensagem de retorno
     * @throws IOException
     * @throws Exception 
     */
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
        if (retorno.contains("ERROR")){ 
            throw  new Exception("Conexão com o servidor principal foi recusada");
        }
        sock.close();
        return retorno.trim();
    }
    /***
     * Método cria uma conexão socket para receber uma mensagem pela porta informada
     * @param porta - Porta da comunicação
     * @throws IOException 
     */
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
            Integer respota = TrataOperacao.executa(mensagem);
            OutputStream out = sock.getOutputStream();
            out.write(respota.toString().getBytes());
            sock.close();
            
            throw  new RuntimeException();
            
        }
        
    }
    
}
