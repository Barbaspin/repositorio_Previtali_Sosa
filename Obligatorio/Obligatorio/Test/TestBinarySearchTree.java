import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.binarySearchTree.MyBinarySearchTreeImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestBinarySearchTree {
    @Test
    void TestInsert(){
        MyBinarySearchTreeImpl<Integer,Integer> arboloncio = new MyBinarySearchTreeImpl<>(0,50);
        arboloncio.insert(2,52);
        arboloncio.insert(3,53);
        arboloncio.insert(1,51);
        arboloncio.insert(-2,-52);
        arboloncio.insert(-1,-51);
        arboloncio.insert(-3,-53);
        /* Creamos un árbol con raíz 0 e insertamos varios nodos.
        Luego, usamos algunos de los valores insertados para revisar si se insertó correctamente */

        assertEquals(-3,arboloncio.getRoot().getLeftChild().getLeftChild().getKey());
        assertEquals(-1,arboloncio.getRoot().getLeftChild().getRightChild().getKey());
        assertEquals(1,arboloncio.getRoot().getRightChild().getLeftChild().getKey());
    }

    @Test
    void TestFind(){
        MyBinarySearchTreeImpl<Integer,Integer> arboloncio = new MyBinarySearchTreeImpl<>(0,50);
        arboloncio.insert(2,52);
        arboloncio.insert(3,53);
        arboloncio.insert(1,51);
        arboloncio.insert(-2,-52);
        arboloncio.insert(-1,-51);
        arboloncio.insert(-3,-53);

        /*Creamos el mismo árbol que hicimos en el otro test.
        Luego, usamos la función find para encontrar la data del nodo con key 1, y también lo buscamos a mano */

        assertEquals(arboloncio.getRoot().getRightChild().getLeftChild().getData(),arboloncio.find(1));


    }
    @Test
    void TestDelete(){
        MyBinarySearchTreeImpl<Integer,Integer> miArbol = new MyBinarySearchTreeImpl<>(5,5);
        miArbol.insert(3,3);
        miArbol.insert(4,4);
        miArbol.insert(2,2);
        miArbol.insert(8,7);
        miArbol.insert(7,6);
        miArbol.insert(9,8);
        miArbol.insert(1,1);
        miArbol.insert(6,2);
        //Creamos un árbol nuevo, e insertamos varios nodos

        miArbol.delete(3);
        assertEquals(2,miArbol.getRoot().getLeftChild().getKey());
        assertEquals(1,miArbol.getRoot().getLeftChild().getLeftChild().getKey());
        assertEquals(4,miArbol.getRoot().getLeftChild().getRightChild().getKey());
        //Borramos el nodo con Key 3, y revisamos si el árbol sigue bien armado

        miArbol.delete(8);
        assertEquals(7,miArbol.getRoot().getRightChild().getKey());
        assertEquals(6,miArbol.getRoot().getRightChild().getLeftChild().getKey());
        assertEquals(9,miArbol.getRoot().getRightChild().getRightChild().getKey());
        //repetimos el proceso, pero eliminando ahora el nodo con Key 8

    }
}
