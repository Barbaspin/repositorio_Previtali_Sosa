import uy.edu.um.prog2.adt.HashCode.HashTable;
import uy.edu.um.prog2.adt.HashCode.HashTableImpl;
import uy.edu.um.prog2.adt.HashCode.Node;
import uy.edu.um.prog2.adt.binarySearchTree.MyBinarySearchTreeImpl;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;


public class OrdenarCSV {

    //es un hash con key pais, dentro tiene hashes con key fecha y dentro tiene listas
    HashTable<String,MyLinkedListImpl<Cancion>> hashFechas;

    //constructor
    public OrdenarCSV(){
        Node<String,MyLinkedListImpl<Cancion>> nodo= new Node<>(null,null);
        this.hashFechas = new HashTableImpl<>(nodo,500);
        String inputFilePath = "universal_top_spotify_songs.csv"; // Ruta del archivo CSV de entrada

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\",\"");
                MyLinkedListImpl<String> fila = new MyLinkedListImpl<>();
                for (String value : values) {
                    fila.add(value);
                }
                Cancion tempCancion = new Cancion(fila);
                String fechaTemp = tempCancion.getFecha();
                if (!hashFechas.contains(fechaTemp)){
                    MyLinkedListImpl<Cancion> nuevaFecha = new MyLinkedListImpl<>();
                    hashFechas.put(fechaTemp,nuevaFecha);
                }
                hashFechas.get(fechaTemp).add(tempCancion);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
//        //leer csv guarda en una lista las canciones con sus atributos
//        LeerCSV leerCsv = new LeerCSV();
//        MyLinkedListImpl<Cancion> data = leerCsv.getDatos();
//        Node<String,MyLinkedListImpl<Cancion>> nodo= new Node<>(null,null);
//        this.hashFechas = new HashTableImpl<>(nodo,500);
//        this.fila0 = data.get(0);
//        for (int i = 1; i < data.size(); i++){
//
////            LocalTime tiempo = LocalTime.now();
//
//            Cancion tempCancion = data.get(i);
//            String fechaTemp = tempCancion.getFecha();
//            if (!hashFechas.contains(fechaTemp)){
//                MyLinkedListImpl<Cancion> nuevaFecha = new MyLinkedListImpl<>();
//                hashFechas.put(fechaTemp,nuevaFecha);
//            }
//            hashFechas.get(fechaTemp).add(tempCancion);
//
////            Duration diferencia = Duration.between(tiempo,LocalTime.now());
////            System.out.println("nanosegundos: " + diferencia.getNano());
//




    public HashTable<String, MyLinkedListImpl<Cancion>> getHashFechas() {
        return hashFechas;
    }

    public void setHashFechas(HashTable<String, MyLinkedListImpl<Cancion>> hashFechas) {
        this.hashFechas = hashFechas;
    }
}
