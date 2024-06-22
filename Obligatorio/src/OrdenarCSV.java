import uy.edu.um.prog2.adt.HashCode.HashTable;
import uy.edu.um.prog2.adt.HashCode.HashTableImpl;
import uy.edu.um.prog2.adt.HashCode.Node;
import uy.edu.um.prog2.adt.binarySearchTree.MyBinarySearchTreeImpl;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class OrdenarCSV {
    //es un hash con key fecha, dentro tiene una lista con Canciones
    private HashTable<String,MyLinkedListImpl<Cancion>> hashFechas;
    private LocalDate fechaMinD;
    private LocalDate fechaMaxD;

    //constructor
    public OrdenarCSV(){
        fechaMaxD = LocalDate.parse("2023-12-25", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        fechaMinD = LocalDate.parse("2023-12-25", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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
                //buscamos cuales son la primer y ultima fecha en el csv
                if (!fechaTemp.equals("snapshot_date")){
                    LocalDate fechaComparar = LocalDate.parse(fechaTemp, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    if (fechaComparar.isBefore(fechaMinD)){
                        fechaMinD = fechaComparar;
                    } else if (fechaComparar.isAfter(fechaMaxD)) {
                        fechaMaxD = fechaComparar;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }





    public HashTable<String, MyLinkedListImpl<Cancion>> getHashFechas() {
        return hashFechas;
    }
    public void setHashFechas(HashTable<String, MyLinkedListImpl<Cancion>> hashFechas) {
        this.hashFechas = hashFechas;
    }

    public LocalDate getFechaMinD() {
        return fechaMinD;
    }
    public void setFechaMinD(LocalDate fechaMinD) {
        this.fechaMinD = fechaMinD;
    }

    public LocalDate getFechaMaxD() {
        return fechaMaxD;
    }
    public void setFechaMaxD(LocalDate fechaMaxD) {
        this.fechaMaxD = fechaMaxD;
    }
}
