
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author akina
 */
public class RepositorioServerImpl extends java.rmi.server.UnicastRemoteObject implements IRepositorioServer{
    
    //TreeMap para salvar as contas, com a chave sendo o numConta (numero da conta)
    Map<Integer,Conta> contas;
    
    public RepositorioServerImpl()
            throws java.rmi.RemoteException {
        super();
        contas = new TreeMap<>();
    }
    
    
    /**
     * Classe retorna os dados e o saldo da conta requisitada através do numConta passado
     * Controle de Concorrência = Bloqueio de Leitura (ReadLock)
     * Bloqueio em modo compartilhado, outra transação pode também obter o readlock. 
     * @param numConta
     * @return dadosConta
     * @throws java.rmi.RemoteException
     */
    public String consultarSaldo(int numConta)
        throws java.rmi.RemoteException, InterruptedException{
            String dadosConta = 
                    "\n\nNumero da Conta: " + contas.get(numConta).getNumConta() + "\n" +
                    "Cliente: " + contas.get(numConta).getNomeCliente() + "\n" +
                    "Banco: " + contas.get(numConta).getNomeBanco() + "\n" +
                    "Agencia: " + contas.get(numConta).getNumAgencia() + "\n" +
                    "------------------------- -- ------------------------\n" +
                    "SALDO: " + contas.get(numConta).getSaldo() + "\n\n";
            //System.out.println(dadosConta);
            
            Thread.sleep(20000);

            return dadosConta;
    }
    
    /**
     * Recebe o numero da conta e o valor do deposito
     * Retorna o valor do saldo atualizado (somado com o deposito)
     * Controle de concorrencia = bloqueio de escrita (writelock)
     * Bloqueio em modo exclusivo, outras transações não podem obter writelock e readlock
     * @param numConta
     * @param deposito
     * @return saldo
     */
    
    public float realizarDeposito(int numConta, float deposito)
        throws java.rmi.RemoteException, InterruptedException{
            float saldo;

            saldo = contas.get(numConta).getSaldo();
            saldo = saldo + deposito;
            Thread.sleep(20000);
            contas.get(numConta).setSaldo(saldo); //atualiza saldo

            return saldo;
    }
    
    /**
     * Recebe o numero da conta e o valor de saque
     * Retorna o saldo atualizado (subtraído o valor do saque)
     * Controle de concorrencia = bloqueio de escrita (writelock)
     * Bloqueio em modo exclusivo, outras transações não podem obter writelock e readlock
     * @param numConta
     * @param saque
     * @return saldo
     */
    public float realizarSaque(int numConta, float saque)
        throws java.rmi.RemoteException, InterruptedException{
            float saldo;

            saldo = contas.get(numConta).getSaldo();
            saldo = saldo - saque;
            Thread.sleep(20000);
            contas.get(numConta).setSaldo(saldo); //atualiza saldo

            return saldo;
    }
    
    
    /**
     * Retira o valor de transferencia da conta 1 e acrescenta na conta 2
     * Controle de concorrencia = bloqueio de escrita (writelock)
     * Bloqueio em modo exclusivo, outras transações não podem obter writelock e readlock
     * @param numConta1
     * @param numConta2
     * @param valorTransferencia 
     */
    public void realizarTransferencia(int numConta1, int numConta2, float valorTransferencia)
        throws java.rmi.RemoteException, InterruptedException{
            float saldoConta1 = contas.get(numConta1).getSaldo() - valorTransferencia;
            float saldoConta2 = contas.get(numConta2).getSaldo() + valorTransferencia;
            Thread.sleep(20000);
            contas.get(numConta1).setSaldo(saldoConta1);
            contas.get(numConta2).setSaldo(saldoConta2);
    }
    
    public Conta encontraConta(int numConta)
        throws java.rmi.RemoteException{
            Conta conta = contas.get(numConta);
            //System.out.println("Teste: encontrou a conta no método encontraConta: " + 
               //                 conta.getNumConta() + "\nCliente: " + conta.getNomeCliente()); //apagar depois
            return conta;
    }
    
    public void preencheRepositorio()
        throws java.rmi.RemoteException{
        
            Conta conta1 = new Conta();
            conta1.setNumConta(123);
            conta1.setNomeBanco("Banco do Brasil");
            conta1.setNumAgencia(001);
            conta1.setNomeCliente("Akina Kurita");
            conta1.setSaldo(1000);
            conta1.setBloqueadoLeitura(false);
            conta1.setBloqueadoEscrita(false);
            contas.put(conta1.getNumConta(), conta1);

            Conta conta2 = new Conta();
            conta2.setNumConta(456);
            conta2.setNomeBanco("Banco do Brasil");
            conta2.setNumAgencia(002);
            conta2.setNomeCliente("Marcos Brunelli");
            conta2.setSaldo(1000);
            conta2.setBloqueadoLeitura(false);
            conta2.setBloqueadoEscrita(false);
            contas.put(conta2.getNumConta(), conta2);

            Conta conta3 = new Conta();
            conta3.setNumConta(789);
            conta3.setNomeBanco("Banco do Brasil");
            conta3.setNumAgencia(001);
            conta3.setNomeCliente("Leonardo Guerra");
            conta3.setSaldo(1000);
            conta3.setBloqueadoLeitura(false);
            conta3.setBloqueadoEscrita(false);
            contas.put(conta3.getNumConta(), conta3);

            System.out.println("Repositorio Preenchido com dados!");
            //System.out.println("Contas:" + conta1.getNumConta() + "-" + conta2.getNumConta()
              //      + "-" + conta3.getNumConta() + "\n");
        
    }
    
    /*
         -- Controle de Concorrência --
    */
    
    public String recebeDadosCliente(int opcao, int idCliente,
             ArrayList<Integer> idContasSelecionadas, float valor) 
            throws RemoteException, InterruptedException{ 
        
        String retorno = "";
            
            Requisicao requisicao = new Requisicao();
            requisicao.setOpcaoOperacao(opcao);
            requisicao.setIdCliente(idCliente);
            requisicao.setValor(valor);
            
            /*IRepositorioServer repositorio = (IRepositorioServer) 
                    Naming.lookup("//127.0.0.1:1099/RepositorioServer");*/
            
            for(Integer idConta : idContasSelecionadas){
                
                    Conta conta = encontraConta(idConta);
                    requisicao.getContasSelecionadas().add(conta);
                  
            }
        
            retorno = executaRequisicao(requisicao);
        
        return retorno;
        
    }

    
    public String executaRequisicao(Requisicao requisicao) 
            throws RemoteException, InterruptedException{
        
        String retorno;
        
        //se a operação for de consulta
        if((requisicao.getOpcaoOperacao() == 1) &&
                (retornaVerdadeiroSeRequisicaoLiberadaLeitura(requisicao))){
            requisicao.getContasSelecionadas().get(0).setBloqueadoLeitura(true);
            retorno = consultarSaldo(requisicao.getContasSelecionadas().get(0).getNumConta());
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
                    realizarDeposito(requisicao.getContasSelecionadas().get(0).getNumConta(),
                        requisicao.getValor());
                    break;
                case 3:
                    realizarSaque(requisicao.getContasSelecionadas().get(0).getNumConta(),
                        requisicao.getValor());
                    break;
                case 4: 
                    realizarTransferencia(
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
            enfileiraRequisicao(requisicao);
        }
        
        retorno = consultarSaldo(requisicao.getContasSelecionadas().get(0).getNumConta());
        return retorno;
    }

    public void enfileiraRequisicao(Requisicao requisicao) 
            throws InterruptedException, RemoteException{        
        for (Conta conta : requisicao.getContasSelecionadas()){
                conta.getFila().insereRequisicao(requisicao);
                verificaFila(conta.getFila());
            }
    }
    
    
    public Boolean retornaVerdadeiroSeRequisicaoLiberadaEscrita(Requisicao requisicao){
        Boolean requisicaoLiberada = true;
        for (Conta conta : requisicao.getContasSelecionadas()){
            if(conta.isBloqueadoEscrita() || conta.isBloqueadoLeitura()){
                requisicaoLiberada = false;
                System.out.println("-- conta bloqueada para escrita");
            }
        }
        return requisicaoLiberada;
    }
    
    public Boolean retornaVerdadeiroSeRequisicaoLiberadaLeitura(Requisicao requisicao){
        Boolean requisicaoLiberada = true;
        for (Conta conta : requisicao.getContasSelecionadas()){
            if(conta.isBloqueadoEscrita()){
                requisicaoLiberada = false;
                System.out.println("-- conta bloqueada para leitura");
            }
        }
        return requisicaoLiberada;
    }
    
    public void verificaFila(Fila fila) 
            throws InterruptedException, RemoteException{
        while(!fila.verificaListaVazia()){
            System.out.println("-- requisicao entrou na fila");
            //espera 30 segundos
            Thread.sleep(10000);
            Requisicao requisicao = fila.retornaPrimeiroFila();
            
            if((requisicao.getOpcaoOperacao() == 1) &&
                retornaVerdadeiroSeRequisicaoLiberadaLeitura(requisicao)){
               executaRequisicao(requisicao);
               fila.removeRequisicao();
            }
            else if((requisicao.getOpcaoOperacao() != 1) &&
                (retornaVerdadeiroSeRequisicaoLiberadaEscrita(requisicao))){
                executaRequisicao(requisicao); 
                fila.removeRequisicao();
            }
        }
    }
    
    
}


