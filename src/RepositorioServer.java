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
import java.util.Scanner;

/**
 * Classe do banco de dados global
 * @author marcos
 */

public class RepositorioServer {
    public RepositorioServer(){
        
        try {
            IRepositorioServer server = new RepositorioServerImpl();
            Naming.rebind("//127.0.0.1:1099/RepositorioServer", server);
            System.out.println("\nSERVIDOR INICIALIZADO");
            server.preencheRepositorio();
        } catch (Exception e) {
            System.out.println("Erro na classe RepositorioServer: " + e);
        }
        
        
        
    }
    
    public static void main(String args[]) {
        new RepositorioServer();
        
    }
}




