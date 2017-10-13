
public class ArbolBinario {
    NodoBinario raiz;
    
    public ArbolBinario(){
        raiz = null;
    }
    //Método utilizado para crear un nodo
    public boolean crearArbol(Object v){
        raiz = new NodoBinario(v);
        if(raiz == null){return false;}
        System.out.println(raiz.valor);
        return true;
    }
}
