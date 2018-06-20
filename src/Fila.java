
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
public class Fila {

  private List<Object> objetos = new LinkedList<Object>();

  public void insereRequisicao(Object objeto) {
    this.objetos.add(objeto);
  }

  public Object removeRequisicao() {
    return this.objetos.remove(0);
  }

  public boolean verificaListaVazia() {
    return this.objetos.size() == 0;
  }
}
