
import java.rmi.RemoteException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author akina
 */


public interface IRepositorioServer extends java.rmi.Remote{
    
    public String consultarSaldo(int numConta)
            throws java.rmi.RemoteException, InterruptedException;
    
    public float realizarDeposito(int numConta, float deposito)
            throws java.rmi.RemoteException, InterruptedException;
    
    public float realizarSaque(int numConta, float saque)
            throws java.rmi.RemoteException, InterruptedException;
            
    public void realizarTransferencia(int numConta1, int numConta2, float valorTransferencia)
            throws java.rmi.RemoteException, InterruptedException;
    
    public Conta encontraConta(int numConta)
            throws java.rmi.RemoteException;
    
    public void preencheRepositorio()
            throws java.rmi.RemoteException;
    
    public String recebeDadosCliente(int opcao, int idCliente,
             ArrayList<Integer> idContasSelecionadas, float valor) 
            throws RemoteException, InterruptedException;
    
    public String executaRequisicao(Requisicao requisicao) 
            throws RemoteException, InterruptedException;
    
    public void enfileiraRequisicao(Requisicao requisicao) 
            throws InterruptedException, RemoteException;
    
    public void verificaFila(Fila fila) 
            throws InterruptedException, RemoteException;
    
}
