import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeerCSV {
    // Lista para almacenar los datos del CSV
    MyLinkedListImpl<MyLinkedListImpl<String>> datos;

    public LeerCSV() {
//        datos = new ArrayList<>();
        datos = new MyLinkedListImpl<>();
        String inputFilePath = "C:\\Users\\aless\\universal_top_spotify_songs.csv"; // Ruta del archivo CSV de entrada

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
//              String[] values = line.split("\",\"");
                String[] values = line.split(",");
//                List<String> fila = new ArrayList<>();
                MyLinkedListImpl<String> fila = new MyLinkedListImpl<>();
                for (String value : values) {
                    fila.add(value);
                }
                datos.add(fila);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public MyLinkedListImpl<MyLinkedListImpl<String>> getDatos() {
        return datos;
    }

    public static void main(String[] args) {
        LeerCSV leer = new LeerCSV();
        System.out.println(leer.getDatos().get(1).get(1));
    }

}
