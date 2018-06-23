
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author akina
 */
public class Fila implements Serializable{

  private List<Requisicao> requisicoes = new LinkedList<>();
  
  public Fila(){
  } 
      
  public void insereRequisicao(Requisicao requisicao) {
    this.requisicoes.add(requisicao);
  } 

  public Requisicao removeRequisicao() {
    return this.requisicoes.remove(0);
  }

  public boolean verificaListaVazia() {
    return this.requisicoes.size() == 0;
  }
  
  public Requisicao retornaPrimeiroFila(){
      return this.requisicoes.get(0);
  }
}
