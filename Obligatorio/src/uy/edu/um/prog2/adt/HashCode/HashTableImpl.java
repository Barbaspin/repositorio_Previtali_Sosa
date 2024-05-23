package uy.edu.um.prog2.adt.HashCode;

import javax.print.attribute.standard.NumberOfDocuments;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class HashTableImpl<K,V> implements HashTable<K,V>{

    private Node<K,V>[] arrayHash;
    private int cantidadAgregados=0;
    private float factor;



    //Constructor
    public HashTableImpl(Node<K,V> clazz, int size) {
        arrayHash = (Node<K, V>[]) Array.newInstance(clazz.getClass(), size);
        //asi creamos el array de generics en el cual deben entrar solo nodos
        factor = ( (float)cantidadAgregados) / arrayHash.length;
    }



    //metodos
    @Override
    public void put(K key, V value) {
        Node<K,V> tempNodo = new Node<>(key,value);
        Node<K,V>[] arrayH =this.getArrayHash();
        int hashNodo =tempNodo.getKey().hashCode()%(arrayH.length);
        // tenemos el nodo que vamos a agregar, y la posicion en la que hay que agregarlo

        if (this.getFactor()<0.8){
            //si al array aun le queda/n un/os espacio/s
            //recorremos el array a partir de la posicion definida antes, para poder agregar el nodo
            int i=0;
            //este while comprueba que la posicion este vacia, y que no se haya llegado hasta el final del array
            while (hashNodo+i < arrayH.length && arrayH[hashNodo+i]!=null){
                i++;
            }
            //si la posicion la encuentra en la primera pasada y no existe un nodo, lo agrega
            if (hashNodo+i != arrayH.length && arrayH[hashNodo+i] == null){
                this.getArrayHash()[hashNodo+i]=tempNodo;
            }
            //si en el while se llego hasta el final del array, vuelve a recorrerlo, pero ahora desde el comienzo
            if (hashNodo+i==arrayH.length){
                int j=0;
                while (arrayH[j]!= null && j < hashNodo){
                    j++;
                }
                //si encontro un lugar libre antes de volver a donde comenzo, entonces agrega el nodo
                if (j != hashNodo){
                    this.getArrayHash()[j]=tempNodo;
                }else{
                    //si no hay lugar libre para agregar al nodo (aunque no deberia pasar, porque se comprueba el factor al inicio),
                    //vuelve a intentarlo
                    this.put(key,value);
                }
            }
        }else{
            //si el array tiene mas del 80% del espacio ocupado
            //se crea un nuevo array y se vuelven a insertar los nodos

            //creo un nuevo hash para poder hacer una nueva lista donde poder usar put para agregar los nodos
            Node<K,V> nodo= new Node<>(null,null);
            HashTableImpl<K, V> nuevoHash = new HashTableImpl<>(nodo,this.getArrayHash().length*2);
//            Node<K,V>[] nuevoArray = (Node<K, V>[]) Array.newInstance(tempNodo.getClass(),arrayH.length*2);
            int j =0;
            while(j<arrayH.length){
                if (arrayH[j] !=null){
                    K nuevaKey = arrayH[j].getKey();
                    V nuevoValue = arrayH[j].getValue();
                    nuevoHash.put(nuevaKey,nuevoValue);
                }
                j++;
            }
            //ahora reemplazamos el array viejo por el nuevo
            this.setArrayHash(nuevoHash.getArrayHash());
            //actualizamos el factor con el nuevo array
            this.setFactor( ( (float) this.getCantidadAgregados() )/this.getArrayHash().length);
            //agregamos el nodo que queriamos desde un inicio
            this.put(key,value);
            }
        //actualizamos la cantidad de agregados y el factor
        this.setCantidadAgregados(this.getCantidadAgregados()+1);
        this.setFactor( ( (float) this.getCantidadAgregados() )/this.getArrayHash().length);
    }



    @Override
    public boolean contains(K key) {
        //llamamos a la funcion searchPosicion, si no lo encuentra devuelve false
        //en el caso contrario devuelve true
        if (this.searchPosicion(key)==-1){
            return false;
        }
        return true;
    }

    @Override
    public void remove(K key) {
        //hay que rehacer el array solo que sin el nodo a borrar
        Node<K,V>[] arrayH =this.getArrayHash();
        Node<K,V> nodo= new Node<>(null,null);
        HashTableImpl<K, V> nuevoHash = new HashTableImpl<>(nodo,this.getArrayHash().length);
        int posicion = this.searchPosicion(key);


        int j =0;
        while(j<arrayH.length){
            if (arrayH[j] !=null && j!=posicion){
                K nuevaKey = arrayH[j].getKey();
                V nuevoValue = arrayH[j].getValue();
                nuevoHash.put(nuevaKey,nuevoValue);
            }
            j++;
        }
        //ahora reemplazamos el array viejo por el nuevo
        this.setArrayHash(nuevoHash.getArrayHash());
        this.setCantidadAgregados(this.getCantidadAgregados()-1);
        this.setFactor( ( (float) this.getCantidadAgregados() )/this.getArrayHash().length);
    }


    public int searchPosicion(K key){
        Node<K,V> nodoTemp = new Node<>(key,null);
        Node<K,V>[] arrayH =this.getArrayHash();
        int pos_hash = nodoTemp.getKey().hashCode()%arrayH.length;

        if (arrayH[pos_hash]==null){
            return -1;
        }
        int i =0;
        //si la posicion no esta vacia, entonces se checkea si los nodos son iguales
        //si se encuentra un vacio antes de encontrar el que se busca, entonces no esta contenido
        //si se termina el array sin que haya vacios, se busca desde el principio
        while (pos_hash+i<arrayH.length && arrayH[pos_hash+i]!=null ) {
            if (arrayH[pos_hash + i].equals(nodoTemp)) {
                return pos_hash+i;
            }
            i++;
        }
        if (pos_hash+i!=arrayH.length && arrayH[pos_hash+i]==null){
            return -1;
            //si paro por un vacio, entonces no se contiene
        }
        //si no se encontro en el bucle anterior, lo busca desde el inicio del array
        i=0;
        while (i<pos_hash && arrayH[i]!=null){
            if (arrayH[i].equals(nodoTemp)) {
                return i;
            }
            i++;
        }

        //si no se encontro en ninguna parte, no lo contiene
        return -1;

    }






    //getters y setters
    public Node<K, V>[] getArrayHash() {
        return arrayHash;
    }
    public void setArrayHash(Node<K, V>[] arrayHash) {
        this.arrayHash = arrayHash;
    }

    public int getCantidadAgregados() {
        return cantidadAgregados;
    }
    public void setCantidadAgregados(int cantidadAgregados) {
        this.cantidadAgregados = cantidadAgregados;
    }

    public float getFactor() {
        return factor;
    }
    public void setFactor(float factor) {
        this.factor = factor;
    }
}
