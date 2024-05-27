import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeerCSV {
    public static void main(String[] args) {
        String inputFilePath = "C:\\Users\\aless\\universal_top_spotify_songs.csv"; // Ruta del archivo CSV de entrada

        // Lista para almacenar los datos del CSV
        List<List<String>> datos = new ArrayList<>();

        // Leer el archivo CSV y almacenar los datos en la lista
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                List<String> fila = new ArrayList<>();
                for (String value : values) {
                    fila.add(value);
                }
                datos.add(fila);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Imprimir los datos almacenados en la lista
        for (List<String> fila : datos) {
            for (String valor : fila) {
                System.out.print(valor + " ");
            }
            System.out.println();
        }

        // Aquí puedes hacer operaciones adicionales con los datos almacenados
        // Por ejemplo, podrías filtrar, modificar o analizar los datos.
    }
}