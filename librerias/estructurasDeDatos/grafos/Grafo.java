package librerias.estructurasDeDatos.grafos;
import librerias.estructurasDeDatos.modelos.*;
import librerias.estructurasDeDatos.lineales.*;
import java.util.ArrayList;

/** Clase abstracta Grafo: Base de la jerarquia Grafo, que define el comportamiento
 *  de un grafo.<br> 
 *  No es una interfaz porque incluye el codigo de aquellas operaciones de un grafo 
 *  que son independientes tanto de su tipo como de su implementacion.<br>
 *  
 *  @version Mayo 2012
 */
public abstract class Grafo {
    protected int visitados[];          // Para las operaciones de recorrido
    protected int ordenVisita;          // Orden de visita de los vertices en los recorridos
    protected Cola<Integer> q;          // Para recorridos en anchura del grafo

    protected double distanciaMin[];    // Distancia minima del vertice origen al resto de vertices
    protected int caminoMin[];          // Vertice anterior en el camino mas corto

    protected static final int INFINITO = (Integer.MAX_VALUE)/3; // Distancia a un vertice aun no alcanzado  

    /** Devuelve el numero de vertices del grafo
     * @return int numero de vertices del grafo
     */
    public abstract int numVertices();

    /** Devuelve el numero de aristas del grafo
     * @return int numero de aristas del grafo
     */
    public abstract int numAristas();

    /** Comprueba si la arista (i,j) esta en un grafo.
     * @param i    Vertice origen
     * @param j    Vertice destino
     * @return boolean true si (i,j) est� en el grafo y false en caso contrario
     */
    public abstract boolean existeArista(int i, int j);

    /** Devuelve el peso de la arista (i,j) de un grafo, 0 si dicha arista 
     * no esta en el grafo.
     * @param i    Vertice origen
     * @param j    Vertice destino
     * @return double Peso de la arista (i,j), 0 si no existe.
     */
    public abstract double pesoArista(int i, int j);

    /** Si no esta, inserta la arista (i, j) en un grafo no Ponderado.
     *  @param i    Vertice origen
     *  @param j    Vertice destino
     */
    public abstract void insertarArista(int i, int j);

    /** Si no esta, inserta la arista (i, j) de peso p en un grafo Ponderado.
     * @param i    Vertice origen
     * @param j    Vertice destino
     * @param p    Peso de la arista (i,j)
     */
    public abstract void insertarArista(int i, int j, double p);

    /** Devuelve una Lista Con PI que contiene los adyacentes al vertice i de un grafo.
     * @param i Vertice del que se obtienen los adyacentes
     * @return ListaConPI con los vertices adyacentes a i
     */
    public abstract ListaConPI<Adyacente> adyacentesDe(int i);

    /** Devuelve un String con cada uno de los vertices de un grafo y sus adyacentes, en orden de insercion 
     * @return  String que representa a un grafo
     */               
    public String toString(){
        String res = "" ;  
        for (int  i = 0 ; i < numVertices() ; i++) {
            res += "Vertice: " + i;
            ListaConPI<Adyacente> l = adyacentesDe(i);
            res += (l.esVacia()) ? " sin Adyacentes " :  " con Adyacentes: ";
            for (l.inicio(); !l.esFin() ; l.siguiente()) {
                res +=  l.recuperar() + " ";  
            }
            res += "\n";  
        }
        return res;      
    }  

    /** Devuelve un array con los vertices (int) de un grafo en orden DFS
     *  @return int[] Array con los vertices recorridos de un grafo en orden DFS
     */          
    public int[] toArrayDFS() {
        int res[] = new int[numVertices()];
        visitados = new int[numVertices()];
        ordenVisita = 1;
        for (int  i = 0; i < numVertices(); i++) 
            if ( visitados[i] == 0 ) res = toArrayDFS(i, res); 
        return res;
    }
    // Recorrido DFS del vertice origen de un grafo   
    protected int[] toArrayDFS(int origen, int res[]) { 
        res[ordenVisita] = origen;
        visitados[origen] = ordenVisita++;
        ListaConPI<Adyacente> l = adyacentesDe(origen);
        for (l.inicio(); !l.esFin(); l.siguiente()) {
            Adyacente a = l.recuperar();
            if (visitados[a.destino] == 0) res = toArrayDFS(a.destino, res);
        }
        return res;
    }

    /** Ciclo Hamiltoniano [COMPLETAR]**/
    
    private boolean allVisited(int visitado[]){
        for(int i=0;i<numVertices();i++) if(visitado[i]!=0) return false;
        return true;
    }
    
    private int[] fillAdy(int visitado[]){
        for(int i=0;i<numVertices();i++) if(adyacentesDe(i).esFin()) visitado[i] = 1;
        return visitado;
    }
            
    public boolean hamiltCycle(int v0){  
        int visitado[] = new int[numVertices()];
        visitado = fillAdy(visitado);
        int res[]      = new int[numVertices()];
        return hamiltCycle(v0,v0,visitado,res);
    }
    
    public boolean hamiltCycle(int vAct,int v0,int visitado[],int res[]){
        visitado[vAct] = 1;
        ListaConPI<Adyacente> lpi = adyacentesDe(vAct);
        Adyacente ady = null;
        boolean found = false;
        for(lpi.inicio();!lpi.esFin();lpi.siguiente()){
            ady = lpi.recuperar();
            if(visitado[ady.destino]==0){
                found = hamiltCycle(ady.destino,v0,visitado,res);
                visitado[ady.destino] = 0;
            }
            else{
                if(ady.destino==v0) found = allVisited(visitado);
            }
        }
        return found;
    }
                
    
    /** Devuelve un array con los vertices (int) de un grafo en orden BFS
     *  @return int[] Array con los vertices recorridos de un grafo en orden BFS
     */
    public int[] toArrayBFS() {
        int res[] = new int[numVertices()];
        visitados = new int[numVertices()]; 
        ordenVisita = 1;  
        q = new ArrayCola<Integer>();
        for ( int  i = 0; i < numVertices(); i++ )
            if (visitados[i] == 0) res = toArrayBFS(i, res); 
        return res;
    }  
    // Recorrido BFS del vertice origen de un grafo         
    protected int[] toArrayBFS(int origen, int res[]) { 
        res[ordenVisita] = origen;
        visitados[origen] = ordenVisita++;
        q.encolar(new Integer(origen));
        while (!q.esVacia()) {
            int u = q.desencolar().intValue(); 
            ListaConPI<Adyacente> l = adyacentesDe(u); 
            for (l.inicio(); !l.esFin(); l.siguiente()) {
                int v = l.recuperar().destino;
                if ( visitados[v]==0 ){
                    res[ordenVisita] = v;
                    visitados[v] = ordenVisita++;
                    q.encolar(new Integer(v));
                }
            }  
        }
        return res;
    }


}    