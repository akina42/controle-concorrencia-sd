
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author akina
 */
public class RepositorioServerImpl extends java.rmi.server.UnicastRemoteObject implements IRepositorioServer{
    
    //TreeMap para salvar as contas, com a chave sendo o numConta (numero da conta)
    Map<Integer,Conta> contas = new TreeMap<Integer,Conta>();
    
    public RepositorioServerImpl()
            throws java.rmi.RemoteException {
        super();
        preencheRepositorio();
    }
    
    
    /**
     * Classe retorna os dados e o saldo da conta requisitada através do numConta passado
     * Controle de Concorrência = Bloqueio de Leitura (ReadLock)
     * Bloqueio em modo compartilhado, outra transação pode também obter o readlock. 
     * @param numConta
     * @return dadosConta
     */
    public String consultarSaldo(int numConta){
        String dadosConta = 
                "Numero da Conta: " + contas.get(numConta).getNumConta() + "\n" +
                "Cliente: " + contas.get(numConta).getNomeCliente() + "\n" +
                "Banco: " + contas.get(numConta).getNomeBanco() + "\n" +
                "Agencia: " + contas.get(numConta).getNumAgencia() + "\n" +
                "------------------------- -- ------------------------\n" +
                "SALDO: " + contas.get(numConta).getSaldo() + "\n\n";
        
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
    
    public float realizarDeposito(int numConta, float deposito){
        float saldo;
        
        saldo = contas.get(numConta).getSaldo();
        saldo = saldo + deposito;
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
    public float realizarSaque(int numConta, float saque){
        float saldo;
        
        saldo = contas.get(numConta).getSaldo();
        saldo = saldo - saque;
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
    public void realizarTransferencia(int numConta1, int numConta2, float valorTransferencia){
        
        float saldoConta1 = contas.get(numConta1).getSaldo() - valorTransferencia;
        float saldoConta2 = contas.get(numConta2).getSaldo() + valorTransferencia;
        contas.get(numConta1).setSaldo(saldoConta1);
        contas.get(numConta2).setSaldo(saldoConta2);
    }
    
    public Conta encontraConta(Integer numConta){
        Conta conta = this.contas.get(numConta);
        return conta;
    }
    
    private void preencheRepositorio(){
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
    }
    
}


