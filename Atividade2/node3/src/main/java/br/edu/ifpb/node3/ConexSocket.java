package br.edu.ifpb.node3;

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
    private static final int PORTANODE1 = 1091;
    private static final int PORTANODE2 = 1092;
    //private static final int PORTANODE3 = 1093;
    
    /***
     * Método trata de abir uma conexão de socket para envio de uma mensagem para um host Específico.
     * @param mensagem - Mensagem a ser encaminhada
     * @param porta - Porta de destino do Host
     * @param host - Host de destino
     * @return - Retorna a resposa após o envio da mensagem
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
        if (retorno.contains("ERROR-Conexão recusada")){ 
            throw  new Exception("Conexão com o servidor principal foi recusada");
        }
        sock.close();
        return retorno.trim();
    }
    /***
     * Método cuida dos dados recebidos e verifica sé possível trata-lo ou encaminha para o node que pode resolver
     * @param mensagem - Mensagem que tem a informação a ser tratada
     * @return Resultado após o tratamento das informações repassadas.
     */
    private static String trataInformacao(String mensagem){
        String respota ="" ;
            try {
                respota = String.valueOf(TrataOperacao.executa(mensagem));
            } catch (Exception e) {
                try {
                    System.out.println("Node3 não resolve essa operação encaminhando para node.");
                    respota =  enviaMensagem(mensagem, PORTANODE1, HOST);
                } catch (Exception e1) {
                    try {
                        respota =  enviaMensagem(mensagem, PORTANODE2, HOST);
                    } catch (Exception e3) {
                        respota = e3.getMessage();
                    }
                }
            }
            return respota;
    }
    
    /***
     * Método ecarregado de abrir uma conexsão Socket server para receber um mensagem 
     * @param porta - Porta de conexão do Socket
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
            mensagem = new String(b).trim();
            System.out.println(mensagem);
            String resposta = trataInformacao(mensagem);
            OutputStream out = sock.getOutputStream();
            out.write(resposta.getBytes());
            sock.close();
            throw  new RuntimeException();
        }
        
    }
    

}
