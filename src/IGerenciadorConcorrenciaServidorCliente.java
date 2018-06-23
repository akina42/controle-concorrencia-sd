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
import java.util.List;

public interface IGerenciadorConcorrenciaServidorCliente extends java.rmi.Remote{
   
     public void recebeDadosCliente(int opcao, int idCliente,
          ArrayList<Integer> idContasSelecionadas, float valor)
            throws RemoteException;
     
     public void executaRequisicao(Requisicao requisicao, 
            IRepositorioServer repositorio) 
            throws RemoteException, InterruptedException;
     
    public void enfileiraRequisicao(Requisicao requisicao, 
            IRepositorioServer repositorio) 
            throws InterruptedException, RemoteException; 
    
    public Boolean retornaVerdadeiroSeRequisicaoLiberadaEscrita(Requisicao requisicao)
            throws java.rmi.RemoteException;
    
    public Boolean retornaVerdadeiroSeRequisicaoLiberadaLeitura(Requisicao requisicao)
            throws java.rmi.RemoteException;
    
    public void verificaFila(Fila fila, 
            IRepositorioServer repositorio) 
            throws InterruptedException, RemoteException;
    
    /*public void testeDadoRecebido(int valor, int id, ArrayList<Integer> array, float val)
            throws RemoteException;*/
            

}
