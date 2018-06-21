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
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GerenciadorConcorrenciaServidorClienteImpl 
        extends java.rmi.server.UnicastRemoteObject 
        implements IGerenciadorConcorrenciaServidorCliente{
    
    public GerenciadorConcorrenciaServidorClienteImpl()
            throws java.rmi.RemoteException {
        super();
    }
    
    //TODO melhorar o código, muitas partes repetidas, deixar mais claro
    
    public void recebeDadosCliente(int opcao, int idCliente,
            ArrayList<Integer> idContasSelecionadas, float valor) 
            throws RemoteException{ 
        IRepositorioServer repositorio = null;
        Requisicao requisicao = null;
        requisicao.setOpcaoOperacao(opcao);
        requisicao.setIdCliente(idCliente);
        requisicao.setValor(valor);
        //requisicao.setContasSelecionadas(idContasSelecionadas);
        
        try {
            repositorio = (IRepositorioServer) 
                    Naming.lookup("//127.0.0.1:1099/RepositorioServer");   
        }
        catch(Exception e){
            System.out.println("Erro ao inicializar a requisicao no gerenciador: " + e);
        }
        
        for(Integer idConta : idContasSelecionadas){
            try {
                Conta conta = repositorio.encontraConta(idConta);
                requisicao.getContasSelecionadas().add(conta);
            } catch (RemoteException ex) {
                Logger.getLogger(GerenciadorConcorrenciaServidorClienteImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
            } 
        }
        
        try {
            executaRequisicao(requisicao, repositorio);
        } catch (InterruptedException ex) {
            Logger.getLogger(GerenciadorConcorrenciaServidorClienteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void executaRequisicao(Requisicao requisicao, 
            IRepositorioServer repositorio) throws RemoteException, InterruptedException{
        
        //se a operação for de consulta
        if((requisicao.getOpcaoOperacao() == 1) &&
                retornaVerdadeiroSeRequisicaoLiberadaLeitura(requisicao)){
            requisicao.getContasSelecionadas().get(0).setBloqueadoLeitura(true);
            repositorio.consultarSaldo(requisicao.getContasSelecionadas().get(0).getNumConta());
            requisicao.getContasSelecionadas().get(0).setBloqueadoLeitura(false);
        }
        
        //se for outro tipo de operação
        else if((requisicao.getOpcaoOperacao() != 1) &&
                (retornaVerdadeiroSeRequisicaoLiberadaEscrita(requisicao))){
            
            for (Conta conta : requisicao.getContasSelecionadas()){
                conta.setBloqueadoEscrita(true);
            }
            switch(requisicao.getOpcaoOperacao()){
                case 2:
                    repositorio.realizarDeposito(requisicao.getContasSelecionadas().get(0).getNumConta(),
                        requisicao.getValor());
                    break;
                case 3:
                    repositorio.realizarSaque(requisicao.getContasSelecionadas().get(0).getNumConta(),
                        requisicao.getValor());
                    break;
                case 4: 
                    repositorio.realizarTransferencia(
                            requisicao.getContasSelecionadas().get(0).getNumConta(),
                            requisicao.getContasSelecionadas().get(1).getNumConta(),
                            requisicao.getValor());  
                    break;
                default:
                    System.out.println("Opção inválida para o gerenciador.");
                    break;
            }
            for (Conta conta : requisicao.getContasSelecionadas()){
                conta.setBloqueadoEscrita(false);
            }
        }
        else{
            enfileiraRequisicao(requisicao, repositorio);
        }
    }

    public void enfileiraRequisicao(Requisicao requisicao, 
            IRepositorioServer repositorio) throws InterruptedException, RemoteException{        
        for (Conta conta : requisicao.getContasSelecionadas()){
                conta.getFila().insereRequisicao(requisicao);
                verificaFila(conta.getFila(), repositorio);
            }
    }
    
    
    public Boolean retornaVerdadeiroSeRequisicaoLiberadaEscrita(Requisicao requisicao){
        Boolean requisicaoLiberada = true;
        for (Conta conta : requisicao.getContasSelecionadas()){
            if(conta.isBloqueadoEscrita() || conta.isBloqueadoLeitura()){
                requisicaoLiberada = false;
            }
        }
        return requisicaoLiberada;
    }
    
    public Boolean retornaVerdadeiroSeRequisicaoLiberadaLeitura(Requisicao requisicao){
        Boolean requisicaoLiberada = true;
        for (Conta conta : requisicao.getContasSelecionadas()){
            if(conta.isBloqueadoEscrita()){
                requisicaoLiberada = false;
            }
        }
        return requisicaoLiberada;
    }
    
    public void verificaFila(Fila fila, 
            IRepositorioServer repositorio) throws InterruptedException, RemoteException{
        while(!fila.verificaListaVazia()){
            //espera 30 segundos
            Thread.sleep(30000);
            Requisicao requisicao = fila.retornaPrimeiroFila();
            
            if((requisicao.getOpcaoOperacao() == 1) &&
                retornaVerdadeiroSeRequisicaoLiberadaLeitura(requisicao)){
               executaRequisicao(requisicao, repositorio);
               fila.removeRequisicao();
            }
            else if((requisicao.getOpcaoOperacao() != 1) &&
                (retornaVerdadeiroSeRequisicaoLiberadaEscrita(requisicao))){
                executaRequisicao(requisicao, repositorio); 
                fila.removeRequisicao();
            }
        }
    }
}
