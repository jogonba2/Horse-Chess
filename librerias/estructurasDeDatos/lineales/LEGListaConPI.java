package librerias.estructurasDeDatos.lineales;
import librerias.estructurasDeDatos.modelos.*;
public class LEGListaConPI<E> implements ListaConPI<E>
{
    private NodoLEG<E> pri,ult,pi;
    private int talla;
    
    public LEGListaConPI()
    {
        pri = ult = pi = new NodoLEG<E>(null,null);
        talla = 0;
    }
    
    public void inicio(){ pi = pri; }   
    public void fin(){ pi = ult; }
    public void siguiente(){ pi = pi.siguiente; }
    public boolean esFin(){ return pi==ult; }
    public boolean esVacia(){ return pri==ult; }
    public int talla(){ return talla; }
    
    public E recuperar()
    {
        return pi.siguiente.dato;
    }
    public void insertar(E dato)
    {
        NodoLEG<E> naux = new NodoLEG<E>(dato,null);
        naux.siguiente = pi.siguiente;
        pi.siguiente = naux;
        if(pi==ult) ult = naux;
        pi = pi.siguiente;
    }
    
    public void eliminar(){
        if(pi.siguiente==ult) ult = pi;
        pi.siguiente = pi.siguiente.siguiente;
    }
    
    public String toString()
    {
        NodoLEG<E> naux = pri.siguiente;
        String res = "";
        inicio();
        while(naux!=null){
            res += naux.dato.toString() + " ";
            naux = naux.siguiente;
        }
        return res;
    }
            
}