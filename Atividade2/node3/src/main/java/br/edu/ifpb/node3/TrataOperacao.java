/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.node3;

/**
 *
 * @author laerton
 */
public class TrataOperacao 
{
    private static int diff(int x, int y){
        return x - y;
    }
    
    public static int executa(String mensagem) throws Exception{
        
        if(mensagem.contains("sum")){
            throw  new Exception("ERRO");
        }
        String[] s = mensagem.trim().replace("diff(", "").replace(")", "").split(",");
        return diff(Integer.valueOf(s[0]), Integer.valueOf(s[1]));
                
    }
    
}
