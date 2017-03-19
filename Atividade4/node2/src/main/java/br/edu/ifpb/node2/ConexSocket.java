package br.edu.ifpb.node2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.net.ssl.SSLSocket;

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
        if (retorno.contains("ERROR")){ 
            throw  new Exception("Conexão com o servidor principal foi recusada");
        }
        sock.close();
        return retorno.trim();
    }
    
    private static String persisteMysql(Pessoa p){
        System.out.println(p.getNome() + "Adicionado no Mysql");
        return "Pessoa registrada no banco de dados MySQL";
    }
    private static String persistePostGress(Pessoa p){
        System.out.println(p.getNome() + "Adicionado no Postgress");
        return "Pessoa registrada no banco de dados PostGres";
    }
    
    private static Pessoa convertePessoa(String mensagem){
        Gson g = new Gson();
        return g.fromJson(mensagem, Pessoa.class);
    }
    
    public static void recebeMensagem (int porta) throws IOException, RuntimeException{
        String mensagem = "";
        System.out.println("Servidor ativo!");
        ServerSocket server = new ServerSocket(porta);
        while(true){
            Socket sock = server.accept();
            InputStream in = sock.getInputStream();
            byte[] b = new byte[1024];
            in.read(b);
            mensagem = new String(b).trim();
            Pessoa p = convertePessoa(mensagem);
            String retorno = persisteMysql(p);
            retorno += "/n" + persistePostGress(p);
            
            OutputStream out = sock.getOutputStream();
            out.write(retorno.getBytes());
            
            throw  new RuntimeException();
        }
        
    }
    
}
