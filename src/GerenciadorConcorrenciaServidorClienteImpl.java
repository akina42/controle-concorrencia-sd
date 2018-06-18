/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author akina
 */

import java.rmi.Naming;
import java.security.NoSuchAlgorithmException;

public class GerenciadorConcorrenciaServidorClienteImpl extends java.rmi.server.UnicastRemoteObject implements IGerenciadorConcorrenciaServidorCliente{
    public GerenciadorConcorrenciaServidorClienteImpl()
            throws java.rmi.RemoteException {
        super();
    }
    
    public Boolean verificaBloqueioConta(int numConta){
        Boolean estaBloqueadoEscrita = true;
        Boolean estaBloqueadoLeitura = true;
        try{
            IRepositorioServer repositorio = 
                    (IRepositorioServer) Naming.lookup("//127.0.0.1:1099/RepositorioServer");
            Conta conta = repositorio.encontraConta(numConta);
            estaBloqueadoEscrita = conta.isBloqueadoEscrita();
            estaBloqueadoLeitura = conta.isBloqueadoLeitura();            
        }
        catch(Exception e){
            System.out.println("Conta inexistente. Erro ao acessar: " + e);
        }
        
        //TODO em que momento vou diferenciar esse bloqueio? implementar outro método?
        
        if(estaBloqueadoEscrita || estaBloqueadoLeitura){
            return true;
        }
        return false;
    }
    
    
    public void recebeDadosEntrada(int opcao){
                
    }
        
    
    
    
}

