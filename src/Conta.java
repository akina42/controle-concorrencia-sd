/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marcos
 */
public class Conta {
    
    private int numConta;
    private String nomeCliente;
    private String nomeBanco;
    private int numAgencia;
    private float saldo;
    private boolean bloqueadoLeitura;
    private boolean bloqueadoEscrita;
    

    public Conta() {
    }

    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    public int getNumAgencia() {
        return numAgencia;
    }

    public void setNumAgencia(int numAgencia) {
        this.numAgencia = numAgencia;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public boolean isBloqueadoLeitura() {
        return bloqueadoLeitura;
    }

    public void setBloqueadoLeitura(boolean bloqueadoLeitura) {
        this.bloqueadoLeitura = bloqueadoLeitura;
    }

    public boolean isBloqueadoEscrita() {
        return bloqueadoEscrita;
    }

    public void setBloqueadoEscrita(boolean bloqueadoEscrita) {
        this.bloqueadoEscrita = bloqueadoEscrita;
    }
    
    
    
    
    
    
}
