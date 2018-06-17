
import java.util.Map;
import java.util.TreeMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marcos
 */
public class BDServidorCliente1Impl extends java.rmi.server.UnicastRemoteObject implements IBDServidorCliente1{

    //TreeMap para salvar as contas, com a chave sendo o numConta (numero da conta)
    //Cópia do MAP do banco de dados global
    Map<Integer,Conta> contas = new TreeMap<Integer,Conta>();
    
    public BDServidorCliente1Impl() 
             throws java.rmi.RemoteException {
        super();    
    }
    
    /**
     * Metodo utilizado pelo banco de dados global para atualizar o saldo das contas do 
     * banco de dados, de acordo com as mudanças feitas.
     * @param saldo
     * @param numConta
     * @return 
     */
    public String atualizaBD(float saldo, int numConta){
        contas.get(numConta).setSaldo(saldo);
        return "Banco de Dados 1: contas atualizadas com sucesso!\n";
    }
    
}
