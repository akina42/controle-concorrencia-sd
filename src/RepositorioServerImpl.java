
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
    }
    
    
    /**
     * Classe retorna os dados e o saldo da conta requisitada através do numConta passado
     * Controle de Concorrência = Bloqueio de Leitura (ReadLock)
     * Bloqueio em modo compartilhado, outra transação pode também obter o readlock. 
     * @param numConta
     * @return dadosConta
     */
    public String consultarSaldo(int numConta, int id){
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
    
    public float realizarDeposito(int numConta, float deposito, int id){
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
    public float realizarSaque(int numConta, float saque, int id){
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
    public void realizarTransferencia(int numConta1, int numConta2, float valorTransferencia, int id){
        
        float saldoConta1 = contas.get(numConta1).getSaldo() - valorTransferencia;
        float saldoConta2 = contas.get(numConta2).getSaldo() + valorTransferencia;
        contas.get(numConta1).setSaldo(saldoConta1);
        contas.get(numConta2).setSaldo(saldoConta2);
    }
    
    public Conta encontraConta(Integer numConta){
        Conta conta = this.contas.get(numConta);
        return conta;
    }
    
}


