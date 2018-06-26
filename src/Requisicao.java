
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author akina
 */
public class Requisicao{
    
    private Integer opcaoOperacao;
    private Integer idCliente;
    private ArrayList<Conta> contasSelecionadas = new ArrayList<>(); //tipo conta?
    private float valor;
    
    public Requisicao(){
    }

    public Integer getOpcaoOperacao() {
        return opcaoOperacao;
    }

    public void setOpcaoOperacao(Integer opcaoOperacao) {
        this.opcaoOperacao = opcaoOperacao;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public ArrayList<Conta> getContasSelecionadas() {
        return contasSelecionadas;
    }

    public void setContasSelecionadas(ArrayList<Conta> contasSelecionadas) {
        this.contasSelecionadas = contasSelecionadas;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
 
    
}
