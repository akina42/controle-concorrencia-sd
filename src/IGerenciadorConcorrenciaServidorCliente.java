/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author akina
 */

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IGerenciadorConcorrenciaServidorCliente extends java.rmi.Remote{
    
     public Boolean verificaBloqueioConta(int numConta)
             throws java.rmi.RemoteException;
     
     public void recebeDadosEntradaAlteracao(int opcao, int idCliente,
            ArrayList<Integer> contasSelecionadas, float valor)
             throws java.rmi.RemoteException;
     
     public void recebeDadosEntradaConsulta(int opcao, int idCliente,
            ArrayList<Integer> contasSelecionadas)
            throws java.rmi.RemoteException;
             
}
