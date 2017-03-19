/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.node4;

/**
 *
 * @author laerton
 */
public class TrataOperacao 
{
    private static int sum(int x, int y){
        return x + y;
    }
    
    private static  int diff(int x , int y){
        return x - y;
    }
    
    /***
     * Método extecuta a operação recebida
     * @param mensagem - Operação recebida 
     * @return resultado da operação
     * @throws Exception 
     */
    public static int executa(String mensagem) {
        String[] s = mensagem.trim().replace("diff", "").replace(")", "").replace("sum", "").replace("(", "").split(",");
        if(mensagem.contains("diff")){
            return diff(Integer.valueOf(s[0]), Integer.valueOf(s[1]));
        }
        return sum(Integer.valueOf(s[0]), Integer.valueOf(s[1]));
    }
    
}
