import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.HashCode.HashTableImpl;
import uy.edu.um.prog2.adt.HashCode.Node;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHash {
    @Test
    void TestPut(){
        //creamos un nodo, y con ese nodo creamos el hash (el nodo todavia no esta adentro)
        Node<Integer,Integer> nodo= new Node<>(1,1);
        HashTableImpl<Integer, Integer> mihash = new HashTableImpl<>(nodo,3);

        //agregamos nodos al array
        mihash.put(1,1);
        mihash.put(8,5);
        mihash.put(2,5);
        mihash.put(4,2);

        //creamos un nodo y averiguamos cual deberia ser su posicion basandonos en su hashCode
        Node<Integer,Integer> nodo2= new Node<>(4,2);
        int posicion = nodo2.getKey().hashCode()%mihash.getArrayHash().length;

        //verificamos que el array se haya agrandado
        assertEquals(6,mihash.getArrayHash().length);
        //verificamos que el nodo con Key 4 se haya agregado en la posicion correcta
        assertEquals(4,mihash.getArrayHash()[posicion].getKey());
}
    @Test
    void TestMismaPosicionPut(){
        Node<Integer,Integer> nodo= new Node<>(1,1);
        HashTableImpl<Integer, Integer> mihash = new HashTableImpl<>(nodo,6);
        mihash.put(1,1);
        mihash.put(7,5);
        mihash.put(3,5);
        mihash.put(4,2);
        Node<Integer,Integer> nodo2= new Node<>(7,2);
        int posicion = nodo2.getKey().hashCode()%mihash.getArrayHash().length;
        assertEquals(7,mihash.getArrayHash()[posicion+1].getKey());
        //encontro que su posicion estaba ocupada y fue a la derecha


    }

    @Test
    void TestContains(){
        Node<Integer,Integer> nodo= new Node<>(1,1);
        HashTableImpl<Integer, Integer> mihash = new HashTableImpl<>(nodo,6);
        mihash.put(1,1);
        mihash.put(7,5);
        mihash.put(3,5);
        mihash.put(4,2);

        assertEquals(true,mihash.contains(1));
        assertEquals(true,mihash.contains(3));
        assertEquals(true,mihash.contains(4));
        assertEquals(true,mihash.contains(7));
        assertEquals(false,mihash.contains(10));


    }
    @Test
    void TestRemove(){
        Node<Integer,Integer> nodo= new Node<>(1,1);
        HashTableImpl<Integer, Integer> mihash = new HashTableImpl<>(nodo,6);
        mihash.put(1,1);
        mihash.put(7,5);
        mihash.put(3,5);
        mihash.put(4,2);
        mihash.put(13,8);

//        mihash.remove(7);
//
//        assertEquals(false,mihash.contains(7));
//        mihash.remove(100); //no se rompe
//
//        mihash.remove(1);
//        assertEquals(false,mihash.contains(1));
        assertEquals(1,mihash.getArrayHash()[1].getKey());
        assertEquals(true,mihash.contains(1));
        mihash.remove(1);
        assertEquals(false,mihash.contains(1));
        assertEquals(7,mihash.getArrayHash()[1].getKey());
        assertEquals(13,mihash.getArrayHash()[2].getKey());









    }

}
