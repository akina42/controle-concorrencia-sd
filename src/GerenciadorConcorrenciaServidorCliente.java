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

public class GerenciadorConcorrenciaServidorCliente {
    public GerenciadorConcorrenciaServidorCliente(){
        try {
            IGerenciadorConcorrenciaServidorCliente clientServer = 
                    new GerenciadorConcorrenciaServidorClienteImpl();
            Naming.rebind("//127.0.0.1:1099/GerenciadorConcorrenciaServidorCliente", clientServer);
            System.out.println("\nCLIENTE / SERVIDOR");
        } catch (Exception e) {
            System.out.println("Erro no servidor de gerenciador de concorrência: " + e);
        }
    }
    
    public static void main(String args[]) {
        new GerenciadorConcorrenciaServidorCliente();
    }
}
