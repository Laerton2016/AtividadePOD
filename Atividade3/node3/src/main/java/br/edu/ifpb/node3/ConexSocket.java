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
    
    private static final int PORTANODE2 = 1092;
    private static final int PORTANODE4 = 1094;
    
    /***
     * Método encarregado de criar uma conexão socket pela porta informada e host de envio de mensagem.
     * @param mensagem - Mensagem a ser enviada
     * @param porta Porta do host que será enviado a mensagem
     * @param host Host de destino
     * @return String de resposta após a mensagem enviada
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
     * Método cria uma conexão socket para receber mensagens pela porta informada
     * @param porta porta para conmunicação
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
            String respota = resolve(mensagem) ;
            OutputStream out = sock.getOutputStream();
            out.write(respota.getBytes());
            sock.close();
            throw  new RuntimeException();
            
        }
        
    }
    /***
     * Método resolve para qual node deve encaminhar a informação e restorna o resultado.
     * @param mensagem - Mensagem a ser encaminhada
     * @return String com resposta da mensagem encaminhada.
     */
    private static String resolve (String mensagem){
        String respota = "";
        try {
               if(mensagem.contains("diff")){
                   respota = enviaMensagem(mensagem, PORTANODE4, HOST);
               }else{
                   respota = enviaMensagem(mensagem, PORTANODE2, HOST);
               }
            } catch (Exception e) {
                    System.out.println("Node não resolve essa operação.");
                    respota = e.getMessage();
            }finally{
                return respota;
        }
    }
    
}
