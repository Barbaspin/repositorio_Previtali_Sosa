import uy.edu.um.prog2.adt.binarySearchTree.MyBinarySearchTreeImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        String fechaInicio = "2024-05-13";
        String fechaFin = "2024-05-15";
        LocalDate fechaInicioDate = LocalDate.parse(fechaInicio, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate fechaFinDate = LocalDate.parse(fechaFin, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int difDias = 0;
        while (fechaInicioDate.plusDays(difDias).isBefore(fechaFinDate)){
            difDias++;
        }

    }
}
