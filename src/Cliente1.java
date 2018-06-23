
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author marcos
 */
public class Cliente1 {
    

    public static void main(String[] args){ 

        try{
            
            IGerenciadorConcorrenciaServidorCliente gerenciador = (IGerenciadorConcorrenciaServidorCliente) Naming.lookup("//127.0.0.1:1099/GerenciadorConcorrenciaServidorCliente");
            Scanner entrada = new Scanner(System.in);
            
            ArrayList<Integer> contasSelecionadas = new ArrayList<>();
            int id = 1;
            int conta=0, contadest=0;
            float valor=0;
            
            System.out.println("\n\tBEM VINDO! \n\t SELECIONE A OPERAÇÃO QUE DESEJA REALIZAR: \n");
            System.out.println("\t1 - Consultar Saldo");
            System.out.println("\t2 - Realizar Depósito");
            System.out.println("\t3 - Realizar Saque");
            System.out.println("\t4 - Realizar Transferência");
            System.out.println("\t5 - Sair");
            System.out.println("\tPor favor, digite sua opção: ");
            int opcao = entrada.nextInt();
            
            switch(opcao){
                case 1: System.out.println("\tPor favor, digite o numero da conta: ");
                        conta = entrada.nextInt();
                        contasSelecionadas.add(conta);
                        gerenciador.recebeDadosCliente(1, id, contasSelecionadas, 0);
                        break;
                        
                case 2: System.out.println("\tPor favor, digite o numero da conta: ");
                        conta = entrada.nextInt();
                        System.out.println("\tQual o valor que deseja depositar?: ");
                        valor = entrada.nextFloat();
                        contasSelecionadas.add(conta);
                        gerenciador.recebeDadosCliente(2, id, contasSelecionadas, valor);
                        break;
                case 3: 
                        System.out.println("\tPor favor, digite o numero da conta: ");
                        conta = entrada.nextInt();
                        System.out.println("\tQual o valor que deseja sacar?: ");
                        valor = entrada.nextFloat();
                        contasSelecionadas.add(conta);
                        gerenciador.recebeDadosCliente(3, id, contasSelecionadas, valor);
                        
                        break;
                case 4: System.out.println("\tPor favor, digite o numero da conta remetente: ");
                        conta = entrada.nextInt();
                        System.out.println("\tPor favor, digite o numero da conta destinatária: ");
                        contadest = entrada.nextInt();
                        System.out.println("\tQual o valor que deseja transferir?: ");
                        valor = entrada.nextFloat();
                        contasSelecionadas.add(conta);
                        contasSelecionadas.add(contadest);
                        gerenciador.recebeDadosCliente(4, id, contasSelecionadas, valor);
                        
                        break;
                case 5: System.out.println("\tAté mais!\n"); break;
                default: System.out.println("\tPor favor, digite uma opção válida!\n");
            }
            
        }catch(Exception e){
             System.out.println("Erro na classe Cliente1: " + e);    
        }
   
    }
      
 }
