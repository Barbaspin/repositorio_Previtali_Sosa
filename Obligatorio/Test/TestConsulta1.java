import Exceptions.DateOutOFBounds;
import Exceptions.DatosDesordenados;
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
        assertThrows(DateOutOFBounds.class,()->{
            this.pruebaSpotify.consultaTop10PaisFecha("UY","1231-01-01");
        });

    }

    @Test
    public void TestTop5Canciones(){
        assertThrows(DateOutOFBounds.class,()->{
            this.pruebaSpotify.consultaMasTop50("1231-01-01");
        });
    }

    @Test
    public void TestTop7Artistas(){
        assertThrows(DateOutOFBounds.class,()->{
            this.pruebaSpotify.consultaMasArtistasTop50("1014-01-15","2024-02-14");
        });
        assertThrows(DatosDesordenados.class,()->{
            this.pruebaSpotify.consultaMasArtistasTop50("2024-02-15","2024-02-14");
        });
    }

    @Test
    public void TestAparicionesArtista(){
        assertThrows(DateOutOFBounds.class,()->{
            this.pruebaSpotify.cantidadArtistaEnFecha("feid","1234-10-11");
        });
    }
    @Test
    public void TestConsultaTempo(){
        assertThrows(DateOutOFBounds.class,()->{
            this.pruebaSpotify.cantidadTempo("0","1","1234-10-10","2014-11-12");
        });
        assertThrows(DatosDesordenados.class,()->{
            this.pruebaSpotify.cantidadTempo("1","0","2023-11-12","2023-11-13");
        });
        assertThrows(DatosDesordenados.class,()->{
            this.pruebaSpotify.cantidadTempo("0","1","2023-11-14","2023-11-13");
        });
    }
}
