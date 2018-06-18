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

public interface IGerenciadorConcorrenciaServidorCliente extends java.rmi.Remote{
    
     public Boolean verificaBloqueioConta(int numConta)
             throws java.rmi.RemoteException;
     
     public void recebeDadosEntrada(int opcao)
             throws java.rmi.RemoteException;
             
}
