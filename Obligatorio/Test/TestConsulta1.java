import Exceptions.DateOutOFBounds;
import Exceptions.DatosDesordenados;
import Exceptions.MalFormatoFecha;
import Exceptions.MalFormatoFloat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestConsulta1 {
    private final Spotify pruebaSpotify;

    public TestConsulta1(){
        pruebaSpotify = new Spotify();
    }
    @Test
    public void TestConsultaPais(){
        //buscamos el error al ingresar un año no registrado
        assertThrows(DateOutOFBounds.class,()->{
            this.pruebaSpotify.consultaTop10PaisFecha("UY","1231-01-01");
        });
        //buscamos el error al ingresar como fecha algo que no lo es
        assertThrows(MalFormatoFecha.class,()->{
            this.pruebaSpotify.consultaTop10PaisFecha("UY","prueba");
        });

    }

    @Test
    public void TestTop5Canciones(){
        //buscamos el error al ingresar un año no registrado
        assertThrows(DateOutOFBounds.class,()->{
            this.pruebaSpotify.consultaMasTop50("1231-01-01");
        });
        //buscamos el error al ingresar como fecha algo que no lo es
        assertThrows(MalFormatoFecha.class,()->{
            this.pruebaSpotify.consultaMasTop50("prueba");
        });
    }

    @Test
    public void TestTop7Artistas(){
        //buscamos el error al ingresar un año no registrado
        assertThrows(DateOutOFBounds.class,()->{
            this.pruebaSpotify.consultaMasArtistasTop50("1014-01-15","2024-02-14");
        });
        //buscamos el error al ingresar como primer fecha una que es posterior a la segunda
        assertThrows(DatosDesordenados.class,()->{
            this.pruebaSpotify.consultaMasArtistasTop50("2024-02-15","2024-02-14");
        });
        //buscamos el error al ingresar como fecha algo que no lo es
        assertThrows(MalFormatoFecha.class,()->{
            this.pruebaSpotify.consultaMasArtistasTop50("prueba","2024-02-14");
        });
    }

    @Test
    public void TestAparicionesArtista(){
        //buscamos el error al ingresar un año no registrado
        assertThrows(DateOutOFBounds.class,()->{
            this.pruebaSpotify.cantidadArtistaEnFecha("feid","1234-10-11");
        });
        //buscamos el error al ingresar como fecha algo que no lo es
        assertThrows(MalFormatoFecha.class,()->{
            this.pruebaSpotify.cantidadArtistaEnFecha("feid","prueba");
        });
    }
    @Test
    public void TestConsultaTempo(){
        //buscamos el error al ingresar un año no registrado
        assertThrows(DateOutOFBounds.class,()->{
            this.pruebaSpotify.cantidadTempo("0","1","1234-10-10","2014-11-12");
        });
        //buscamos el error al ingresar como primer tempo uno que es mayor que el segundo
        assertThrows(DatosDesordenados.class,()->{
            this.pruebaSpotify.cantidadTempo("1","0","2023-11-12","2023-11-13");
        });
        //buscamos el error al ingresar como primer fecha una que es posterior a la segunda
        assertThrows(DatosDesordenados.class,()->{
            this.pruebaSpotify.cantidadTempo("0","1","2023-11-14","2023-11-13");
        });
        //buscamos el error al ingresar como fecha algo que no lo es
        assertThrows(MalFormatoFecha.class,()->{
            this.pruebaSpotify.cantidadTempo("0","1","prueba","2023-11-13");
        });
        //buscamos el error al ingresar como tempo algo que no es un número
        assertThrows(MalFormatoFloat.class,()->{
            this.pruebaSpotify.cantidadTempo("prueba","1","2023-11-14","2023-11-13");
        });
    }
}
