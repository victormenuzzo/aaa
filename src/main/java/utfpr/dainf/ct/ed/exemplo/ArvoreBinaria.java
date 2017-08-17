package utfpr.dainf.ct.ed.exemplo;

import java.util.Stack;

/**
 * UTFPR - Universidade Tecnológica Federal do Paraná
 * DAINF - Departamento Acadêmico de Informática
 * 
 * Exemplo de implementação de árvore binária.
 * @author Wilson Horstmeyer Bogado <wilson@utfpr.edu.br>
 * @param <E> O tipo do valor armazenado nos nós na árvore
 */
public class ArvoreBinaria<E> {
    
    private E dado;
    private ArvoreBinaria<E> esquerda;
    private ArvoreBinaria<E> direita;
    
    // para percurso iterativo
    private boolean inicio = true;
    private Stack<ArvoreBinaria<E>> pilha;
    private ArvoreBinaria<E> ultimoVisitado;

    /**
     * Cria uma árvore binária com dado nulo na raiz.
     */
    public ArvoreBinaria() {
    }

    /**
     * Cria uma árvore binária com dado {@code dado} na raiz.
     * @param valor O dado do nó raiz
     */
    public ArvoreBinaria(E dado) {
        this.dado = dado;
    }
    
    /**
     * Adiciona um nó à esquerda do nó corrente.
     * @param dado O dado associado ao nó inserido.
     * @return A árvore adicionada ao nó
     */
    public ArvoreBinaria<E> insereEsquerda(E dado) {
        ArvoreBinaria<E> e = esquerda;
        esquerda = new ArvoreBinaria<>(dado);
        esquerda.esquerda = e;
        return esquerda;
    }
    
    /**
     * Adiciona um nó à esquerda do nó corrente.
     * @param dado O dado associado ao nó inserido.
     * @return A árvore adicionada ao nó
     */
    public ArvoreBinaria<E> insereDireita(E dado) {
        ArvoreBinaria<E> d = direita;
        direita = new ArvoreBinaria<>(dado);
        direita.direita = d;
        return direita;
    }
    
    /**
     * Implementação padrão que exibe o dado armazenado no nó usando
     * o método {@code toString() }.
     * Pode ser sobrecarregado em classes derivadas para implementar outras
     * formas de visita.
     * @param no O nó a ser visitado
     */
    protected void visita(ArvoreBinaria<E> no) {
        System.out.print(" " + no.dado);
    }
    
    /**
     * Visita os nós da subárvore em-ordem.
     * @param raiz A raiz da subárvore
     */
    public void visitaEmOrdem(ArvoreBinaria<E> raiz) {
        if (raiz != null) {
            ArvoreBinaria.this.visitaEmOrdem(raiz.esquerda);
            visita(raiz);
            ArvoreBinaria.this.visitaEmOrdem(raiz.direita);
        }
    }
    
    private void inicializaPilha() {
        if (pilha == null) {
            pilha = new Stack<>();
        }
    }
    
    /**
     * Retorna o dado na raiz da árvore e atribui o nó visitado à raiz.
     * @return O dado da raiz.
     */
    public ArvoreBinaria<E> primeiroEmOrdem() {
        inicializaPilha();
        pilha.clear();
        ultimoVisitado = this;
        inicio = false;
        return ultimoVisitado;
    }
    
    /**
     * Retorna o dado do próximo nó em-ordem.
     * @return O dado do próximo nó em-ordem.
     */
    public ArvoreBinaria<E> proximoEmOrdem() {
        ArvoreBinaria<E> resultado = null;
        if (inicio) {
            inicializaPilha();
            inicio = false;
            ultimoVisitado = this;
        }
        if (!pilha.isEmpty() || ultimoVisitado != null) {
            while (ultimoVisitado != null) {
                pilha.push(ultimoVisitado);
                ultimoVisitado = ultimoVisitado.esquerda;
            }
            ultimoVisitado = pilha.pop();
            resultado = ultimoVisitado;
            ultimoVisitado = ultimoVisitado.direita;
        }
        inicio = resultado == null;
        return resultado;
    }
    
    /**
     * Visita os nós da árvore em-ordem a partir da raiz.
     */
    public void visitaEmOrdem() {
        ArvoreBinaria.this.visitaEmOrdem(this);
    }
    
    /**
     * Retorna o dado armazenado no nó.
     * @return O dado armazenado no nó.
     */
    public E getDado() {
        return dado;
    }

    /**
     * Atribui um dado ao nó.
     * @param dado O dado a ser atribuído ao nó.
     */
    public void setDado(E dado) {
        this.dado = dado;
    }

    /**
     * Retorna a árvore esqueda.
     * @return A árvore esquerda.
     */
    protected ArvoreBinaria<E> getEsquerda() {
        return esquerda;
    }

    /**
     * Retorna a árvore direita.
     * @return A árvore direita.
     */
    protected ArvoreBinaria<E> getDireita() {
        return direita;
    }
    
}
