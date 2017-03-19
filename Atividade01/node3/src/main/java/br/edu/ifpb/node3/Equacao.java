package br.edu.ifpb.node3;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author laerton
 */
public class Equacao {
    /***
     * Método encarregado de resolver a equação f(x,y) = X^y + Y^x
     * @param x Valor de X
     * @param y Valor de Y
     * @return  Resultado da equação;
     */
    public static int solucao (double x, double y){
        int resposta = 0;
        resposta = (int) (Math.pow(x, y) + Math.pow(y, x));
        return resposta;
    }
}
