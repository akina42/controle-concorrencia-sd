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

public class CentralizadorTransacoesClientServer {
    public CentralizadorTransacoesClientServer(){
        /*
        try {
            IKeyValueClientServer clientServer = new KeyValueClientServerImpl();
            Naming.rebind("//127.0.0.1:1099/KeyValueServiceClient", clientServer);
            System.out.println("\nCLIENTE / SERVIDOR");
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        */
    }
    
    public static void main(String args[]) {
        new CentralizadorTransacoesClientServer();
    }
}
