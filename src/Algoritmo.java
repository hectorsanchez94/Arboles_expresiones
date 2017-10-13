
import java.util.ArrayList;

public class Algoritmo{
    public ArbolBinario ab;    
    ArrayList<NodoBinario> nodos = new ArrayList<>();
    public int[] prioridad(String cadena){
        int[] a = new int[cadena.length()];
        int temp = -1;
        //Detecta suma y resta
        for(int i=0;i<cadena.length();i++){
            String x = cadena.charAt(i)+"";
            if(x.equals("+")||x.equals("-")){
                temp = i;
            }
        }    
        if(temp != -1){
            a[0] = temp;
            return a;
        }
        //Detecta mul y div
        for(int i=0;i<cadena.length();i++){
            String x = cadena.charAt(i)+"";
            if(x.equals("*")||x.equals("/")){
                temp = i;
            }
        }    
        if(temp != -1){
            a[0] = temp;
            return a;
        }
        //Detectar potencia
        for(int i = cadena.length()-1;i>=0;i--){
            String x = cadena.charAt(i)+"";
            if(x.equals("^")){
                a[0] = i;          
            }
        }    
        return a;
    }
    
    //Este método crea el nodo raiz
    public void crearArbol(String cad){
        ab = new ArbolBinario();
        int []p = prioridad(cad);
        if(p.length == 1){
            ab.crearArbol(cad);
        }
        ab.crearArbol(cad.substring(p[0], p[0]+1));
        String pI = cad.substring(0, p[0]), pD = cad.substring(p[0]+1, cad.length());
        ab.raiz.crearNodHs();
        //Esta parte es para el hijo Izquierdo.
        crearNodos(ab.raiz.nodHI, pI);
        //Esta parte es para el hijo Derecho.
        crearNodos(ab.raiz.nodHD, pD);
    }
    
    //Apartir de este metodo se crea las ramificaciones
    public void crearNodos(NodoBinario act, String cad){
        int []p = prioridad(cad);
        if(p.length == 1){
            act.valor = cad;
            return;
        }
        act.valor=cad.substring(p[0], p[0]+1);
        String pI = cad.substring(0, p[0]), pD = cad.substring(p[0]+1, cad.length());
        act.crearNodHs();
        //Esta parte es para el hijo Izquierdo.
        crearNodos(act.nodHI, pI);
        //Esta parte es para el hijo Derecho.
        crearNodos(act.nodHD, pD);
    }
    // Recorre el árbol de forma inorden para obtener la dependecia de cada nodo
    public String recorridoInorden(){
        return recorridoInorden(ab.raiz);
    }
    private String recorridoInorden(NodoBinario papa){
        String inorden = "";
        if(papa == null){
            return "";
        }
        if(papa.nodHI != null){
            inorden += recorridoInorden(papa.nodHI);
        }
        inorden += papa.valor + "  ";
        if (papa.nodHD != null && papa.nodHI != null) {
            nodos.add(papa);
        }
        if(papa.nodHD != null){
            inorden += recorridoInorden(papa.nodHD);
        }
        return inorden;
    }
    /*
    public static void main(String args[]){
        String cad = "a+b*c^d-e/f";
        int []p =  new Algoritmo().prioridad(cad);
        for(int i = 0; i < p.length; i++){
            System.out.println(p[i]);
        }
    }*/
}
