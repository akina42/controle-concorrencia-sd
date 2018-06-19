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

public interface IRepositorioServer extends java.rmi.Remote{
    
    public String consultarSaldo(int numConta, int id)
            throws java.rmi.RemoteException;
    
    public float realizarDeposito(int numConta, float deposito, int id)
            throws java.rmi.RemoteException;
    
    public float realizarSaque(int numConta, float saque, int id)
            throws java.rmi.RemoteException;
            
    public void realizarTransferencia(int numConta1, int numConta2, float valorTransferencia, int id)
            throws java.rmi.RemoteException;
    
    public Conta encontraConta(Integer numConta)
            throws java.rmi.RemoteException;
    
}
