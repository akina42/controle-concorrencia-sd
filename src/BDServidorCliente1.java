
import java.rmi.Naming;


/**
 *
 * @author marcos
 */
public class BDServidorCliente1 {
    public BDServidorCliente1() {
        try{
            
            
            IBDServidorCliente1 server = new BDServidorCliente1Impl();
            Naming.rebind("//127.0.0.1:1099/BDServidorCliente1", server);
            
            //acessando banco de dados global para fazer as transações
            //verificar se é declarado aqui mesmo ou no main
            IRepositorioServer bdGlobal = (IRepositorioServer) Naming.lookup("//127.0.0.1:1099/RepositorioServer");
            //fazer as operações (deposito, saque, etc.)
            
        }catch(Exception e){
             System.out.println("Erro na classe BDCliente1: "+ e);
        }
        
        
    }
    
    public static void main(String[] args){
        new BDServidorCliente1();
    }
}
