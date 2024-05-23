import Exceptions.EmptyStackException;
import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.prog2.adt.stack.MyStack;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestStack {
    @Test
    void Test(){
        //creamos un nuevo Stack
        MyStack<Integer> stackTest = new MyLinkedListImpl<>();
        stackTest.push(1);
        stackTest.push(2);
        stackTest.push(3);
        stackTest.push(4);
        stackTest.push(5);

        //probamos el metodo size y peek
        //size deberia ser 5 ya que agregamos 5 elementos
        assertEquals(5, stackTest.size());
        //nos debe dar 5 ya que este es el ultimo valor que agregamos (LIFO)
        assertEquals(5, stackTest.peek());

        //intentamos sacar el ultimo elemento, que en este caso es el 5
        try {
            assertEquals(5,stackTest.pop());
        } catch (EmptyStackException e) {
            System.out.println("Error al hacer pop");
        }
        //ahora size deberia ser 4
        assertEquals(4, stackTest.size());
    }
}
