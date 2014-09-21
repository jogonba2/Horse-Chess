package librerias.estructurasDeDatos.modelos;

 
/**
 * Modelo Diccionario, orientado a la busqueda por clave en una coleccion de datos
 * @author Profesores de EDA 
 * @version 2011
 */
public interface Map<C, V> {
    // Aade la entrada (c,v) y devuelve el antiguo valor que
    // ten�a dicha clave (o null si no ten�a ning�n valor asociado)
    V insertar(C c, V v);
    // Elimina la entrada con clave c y devuelve su valor asociado
    // (o null si no hay ninguna entrada con dicha clave)
    V eliminar(C c);
    // Busca la clave c y devuelve su informacion asociada
    // o null si no hay una entrada con dicha clave
    V recuperar(C c);
    // Devuelve true si el Map est� vac�o
    boolean esVacio();
    // Devuelve el n�mero de entradas que contiene el Map
    int talla();
    // Devuelve un array con las claves de todas las entradas del Map
    ListaConPI<C> claves();
}

