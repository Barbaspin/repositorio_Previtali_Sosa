import Exceptions.EmptyQueueException;
import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.prog2.adt.queue.MyQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestQueue {
    @Test
    void TestEnqueue(){
        //creamos una queue
        MyQueue<Integer> queueTest = new MyLinkedListImpl<>();
        queueTest.enqueue(1);
        queueTest.enqueue(2);
        queueTest.enqueue(3);
        //aca probamos el contains
        assertEquals(true, queueTest.contains(3));
        assertEquals(false, queueTest.contains(4));
        //aca probamos ver si anda el size
        assertEquals(3, queueTest.size());


        //intentamos sacar un elemento de la queue
        try {
            assertEquals(1,queueTest.dequeue());
        } catch (EmptyQueueException e) {
            System.out.println("El stack esta vacio");

        }

        //aca probamos el size despues de sacar un nodo de la queue
        assertEquals(2, queueTest.size());


        //intentamos sacar un elemento de la queue
        try {
            assertEquals(2,queueTest.dequeue());
        } catch (EmptyQueueException e) {
            System.out.println("El stack esta vacio");
        }

        //aca probamos el size despues de sacar otro nodo de la queue
        assertEquals(1, queueTest.size());

        //intentamos sacar un elemento de la queue
        try {
            assertEquals(3,queueTest.dequeue());
        } catch (EmptyQueueException e) {
            System.out.println("El stack esta vacio");
        }

        //aca probamos el size despues de sacar otro nodo de la queue
        assertEquals(0, queueTest.size());


    }
}
