import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.prog2.adt.linkedlist.MyList;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestLinkedList {
    @Test
    void TestLinkedList1(){
        MyList<Integer> listTest = new MyLinkedListImpl<>();
        listTest.add(1);
        listTest.add(2);
        listTest.add(3);
        listTest.add(4);


        assertEquals(2, listTest.get(1));

        //deberia ser 4 ya que se agregaron 4 valores
        assertEquals(4, listTest.size());

        //probamos el contains
        assertEquals(true, listTest.contains(3));
        assertEquals(false, listTest.contains(9));

        //probamos el remove
        listTest.remove(2);
        assertEquals(3, listTest.size());
        assertEquals(false,listTest.contains(2));

        //volvemos a probar get
        assertEquals(3, listTest.get(1));

    }
}
