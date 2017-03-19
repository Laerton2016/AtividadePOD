package br.edu.ifpb.node2;

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
import javax.net.ssl.SSLSocket;
import jdk.nashorn.internal.runtime.regexp.joni.constants.StackType;

/**
 *
 * @author laerton
 */
public class ConexSocket {
    
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
    
    public static void recebeMensagem (int portaLocal, String hostRedirec, int portaRedirec) throws IOException, Exception{
        String mensagem = "";
        System.out.println("Servidor ativo!");
        ServerSocket server = new ServerSocket(portaLocal);
        while(true){
            Socket sock = server.accept();
            InputStream in = sock.getInputStream();
            byte[] b = new byte[1024];
            in.read(b);
            mensagem = new String(b).trim();
            System.out.println(mensagem);
            //tratando mensagem 
            String[] m = mensagem.split(";");
            if (m[0].equals(m[1])){
               //Respondendo ao resquisitor
               OutputStream out = sock.getOutputStream();
               out.write("0".getBytes());
               sock.close();
            }else
            {
                System.out.println(enviaMensagem(mensagem, portaRedirec, hostRedirec));
            }
            throw  new RuntimeException();
        }
        
    }
    
}
