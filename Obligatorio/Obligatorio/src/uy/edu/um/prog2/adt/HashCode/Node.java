package uy.edu.um.prog2.adt.HashCode;

import java.util.Objects;

public class Node<K,V>{
    //atributos
    private K key; //es la clave de busqueda
    private V value; //es el valor(data)



    //constructor
    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    // Metodos






    //hash y equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?, ?> node = (Node<?, ?>) o;
        return Objects.equals(key, node.key);
    }




    //getters y setters
    public V getValue() {
        return value;
    }
    public void setValue(V value) {
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }
}
